package br.unitins.topicos1.service.pagamento;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.pagamento.PagamentoDTO;
import br.unitins.topicos1.dto.pagamento.PagamentoResponseDTO;
import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.Reserva;
import br.unitins.topicos1.model.TipoPagamento;
import br.unitins.topicos1.repository.PagamentoRepository;
import br.unitins.topicos1.repository.ReservaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

    @Inject
    PagamentoRepository repository;

    @Inject
    ReservaRepository reservaRepository;

    @Override
    @Transactional
    public PagamentoResponseDTO insert(@Valid PagamentoDTO dto) {
        Reserva reserva = reservaRepository.findById(dto.idReserva());
        if (reserva == null) {
            throw new NotFoundException("Reserva não encontrada.");
        }
        Pagamento pagamento = new Pagamento(dto, reserva);
        pagamento.setReserva(reserva);
        repository.persist(pagamento);
        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    @Transactional
    public PagamentoResponseDTO update(@Valid PagamentoDTO dto, Long id) {
        Pagamento pagamento = repository.findById(id);
        if (pagamento == null) {
            throw new NotFoundException("Pagamento não encontrado.");
        }

        pagamento.atualizarComDto(dto);
        repository.persist(pagamento);
        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Pagamento pagamento = repository.findById(id);
        if (pagamento == null) {
            throw new NotFoundException("Pagamento não encontrado.");
        }
        repository.delete(pagamento);
    }

    @Override
    public PagamentoResponseDTO findById(Long id) {
        Pagamento pagamento = repository.findById(id);
        if (pagamento == null) {
            throw new NotFoundException("Pagamento não encontrado.");
        }
        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    public List<PagamentoResponseDTO> findByAll() {
        List<Pagamento> pagamentos = repository.listAll();
        List<PagamentoResponseDTO> dtos = new ArrayList<>();
        for (Pagamento pagamento : pagamentos) {
            dtos.add(PagamentoResponseDTO.valueOf(pagamento));
        }
        return dtos;
    }

    @Override
    public List<PagamentoResponseDTO> findByTipoPagamento(TipoPagamento tipoPagamento) {
        List<Pagamento> pagamentos = repository.findByTipoPagamento(tipoPagamento);
        return pagamentos.stream()
                .map(PagamentoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
