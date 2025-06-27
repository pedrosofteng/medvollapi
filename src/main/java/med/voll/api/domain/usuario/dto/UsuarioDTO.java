package med.voll.api.domain.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        String login,
        String senha) {
}
