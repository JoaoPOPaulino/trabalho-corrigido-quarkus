package br.unitins.topicos1.dto.comentario;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record ComentarioDTO(@NotBlank(message = "O conteúdo não pode ser vazio.") String conteudo,
                @NotNull(message = "A data de criação não pode ser nula.") @PastOrPresent(message = "A data de criação deve ser no passado ou presente.") LocalDateTime dataCriacao,
                @NotNull(message = "O ID do usuário é obrigatorio.") Long idUsuario) {
}
