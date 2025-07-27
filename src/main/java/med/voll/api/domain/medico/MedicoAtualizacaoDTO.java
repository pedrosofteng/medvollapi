package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.EnderecoDTO;

public record MedicoAtualizacaoDTO(
        @NotNull
        Long id,

        String nome,

        @Pattern(regexp = "\\d{10,11}")
        String telefone,

        EnderecoDTO endereco) {
}
