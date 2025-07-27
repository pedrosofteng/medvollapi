package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.endereco.EnderecoDTO;

public record PacienteAtualizacaoDTO(
        @NotNull
        Long id,

        String nome,

        @Pattern(regexp = "\\d{10,11}")
        String telefone,

        EnderecoDTO endereco
) {
}
