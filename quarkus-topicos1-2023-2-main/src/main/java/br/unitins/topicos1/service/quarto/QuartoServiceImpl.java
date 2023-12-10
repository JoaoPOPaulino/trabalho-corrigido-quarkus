package br.unitins.topicos1.service.quarto;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.quarto.QuartoDTO;
import br.unitins.topicos1.dto.quarto.QuartoResponseDTO;
import br.unitins.topicos1.model.Quarto;
import br.unitins.topicos1.model.TipoQuarto;
import br.unitins.topicos1.repository.QuartoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class QuartoServiceImpl implements QuartoService {

    @Inject
    QuartoRepository repository;

    @Override
    @Transactional
    public QuartoResponseDTO insert(@Valid QuartoDTO dto) {
        Quarto novoQuarto = new Quarto(dto);
        repository.persist(novoQuarto);
        return QuartoResponseDTO.valueOf(novoQuarto);
    }

    @Override
    @Transactional
    public QuartoResponseDTO update(@Valid QuartoDTO dto, Long id) {
        Quarto quarto = repository.findById(id);
        if (quarto == null) {
            throw new NotFoundException("Quarto não encontrado.");
        }
        quarto.atualizarComDTO(dto);
        repository.persist(quarto);
        return QuartoResponseDTO.valueOf(quarto);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Quarto não encontrado.");
        }
    }

    @Override
    public QuartoResponseDTO findById(Long id) {
        Quarto quarto = repository.findById(id);

        if (quarto == null) {
            throw new NotFoundException();
        }

        return QuartoResponseDTO.valueOf(quarto);
    }

    @Override
    public List<QuartoResponseDTO> findByTipo(TipoQuarto tipo) {
        List<Quarto> quartos = repository.findByTipo(tipo);
        return quartos.stream()
                .map(QuartoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuartoResponseDTO> findAll() {
        List<Quarto> quartos = repository.listAll();
        return quartos.stream()
                .map(e -> QuartoResponseDTO.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public QuartoResponseDTO updateNomeImagem(Long id, String nomeImagem) {
        Quarto quarto = repository.findById(id);
        quarto.setNomeImagem(nomeImagem);
        return QuartoResponseDTO.valueOf(quarto);
    }

}