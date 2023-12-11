package br.unitins.topicos1.dto.usuario;

import java.util.List;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Usuario;

public record UsuarioResponseDTO(
                Long id,
                String nome,
                String email,
                String login,
                String senha,
                Perfil perfil,
                List<TelefoneDTO> listaTelefone,
                Endereco endereco) {
        public static UsuarioResponseDTO valueOf(Usuario usuario) {
                return new UsuarioResponseDTO(
                                usuario.getId(),
                                usuario.getNome(),
                                usuario.getEmail(),
                                usuario.getLogin(),
                                usuario.getSenha(),
                                usuario.getPerfil(),
                                usuario.getListaTelefone()
                                                .stream()
                                                .map(t -> TelefoneDTO.valueOf(t)).toList(),
                                usuario.getEndereco());
        }
}
