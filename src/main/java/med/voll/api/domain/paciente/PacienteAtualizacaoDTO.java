package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.Endereco;

public record PacienteAtualizacaoDTO(
        @NotNull
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{10,11}")
        String telefone,
        @NotNull
        @Valid
        Endereco endereco
) {
}
