package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CancelamentoConsultaDTO(
        @NotNull
        Long idConsulta,
        @NotBlank
        String motivoCancelamento
) {
}
