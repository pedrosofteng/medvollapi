package med.voll.api.domain.medico.dto;

import jakarta.validation.constraints.NotNull;

public record MedicoAtualizacaoDTO(
        @NotNull
        Long id,

        String nome,

        String telefone,

        EnderecoDTO endereco) {
}
