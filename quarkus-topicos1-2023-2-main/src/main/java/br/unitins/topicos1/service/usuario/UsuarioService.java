package br.unitins.topicos1.service.usuario;

import java.util.List;

import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface UsuarioService {

    UsuarioResponseDTO insert(@Valid UsuarioDTO dto);

    UsuarioResponseDTO update(@Valid UsuarioDTO dto, Long id);

    void delete(Long id);

    UsuarioResponseDTO findById(Long id);

    List<UsuarioResponseDTO> findByNome(String nome);

    List<UsuarioResponseDTO> findByAll();

    UsuarioResponseDTO findByLoginAndSenha(String login, String senha);

    UsuarioResponseDTO findByLogin(String login);

}
