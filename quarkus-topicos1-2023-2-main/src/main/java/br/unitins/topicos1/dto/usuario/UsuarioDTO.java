package br.unitins.topicos1.dto.usuario;

import java.util.List;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
                @NotBlank(message = "O nome é obrigatório") String nome,
                @NotBlank(message = "O login é obrigatório") String login,
                @NotBlank(message = "A senha é obrigatório") String senha,
                Integer idPerfil,
                List<TelefoneDTO> listaTelefone,
                EnderecoDTO endereco) {

}