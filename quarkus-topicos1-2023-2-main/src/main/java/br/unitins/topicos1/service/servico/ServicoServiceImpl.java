package br.unitins.topicos1.service.servico;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.servico.ServicoDTO;
import br.unitins.topicos1.dto.servico.ServicoResponseDTO;
import br.unitins.topicos1.model.Servico;
import br.unitins.topicos1.repository.ServicoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ServicoServiceImpl implements ServicoService {
    @Inject
    ServicoRepository repository;

    @Override
    @Transactional
    public ServicoResponseDTO insert(@Valid ServicoDTO dto) {
        Servico novoServico = new Servico(dto);
        repository.persist(novoServico);
        return ServicoResponseDTO.valueOf(novoServico);
    }

    @Override
    @Transactional
    public ServicoResponseDTO update(@Valid ServicoDTO dto, Long id) {
        Servico servico = repository.findById(id);
        if (servico == null) {
            throw new NotFoundException("Serviço não encontrado.");
        }
        servico.atualizazrServico(dto);
        repository.persist(servico);
        return ServicoResponseDTO.valueOf(servico);
    }

    @Override
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Serviço não encontrado.");
        }
    }

    @Override
    public ServicoResponseDTO findById(Long id) {

        Servico servico = repository.findById(id);
        if (servico == null) {
            throw new NotFoundException("Serviço não encontrado.");
        }
        return ServicoResponseDTO.valueOf(servico);
    }

    @Override
    public List<ServicoResponseDTO> findAll() {
        List<Servico> servicos = repository.listAll();
        return servicos.stream()
                .map(ServicoResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<ServicoResponseDTO> findByNome(String nome) {
        List<Servico> servicos = repository.findByNome(nome);
        List<ServicoResponseDTO> dtos = new ArrayList<>();
        for (Servico servico : servicos) {
            dtos.add(ServicoResponseDTO.valueOf(servico));
        }
        return dtos;
    }

}
