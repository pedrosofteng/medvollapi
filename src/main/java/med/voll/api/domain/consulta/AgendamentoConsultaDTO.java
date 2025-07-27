package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoConsultaDTO(
        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        LocalDateTime data) {

        /*
        2025-08-10T10:00
        YYYY-MM-DD depois vem o T para separar das horas
        HH:MM:SS
        YYYY-MM-DDTHH:MM:SS os segundos são opcionais
         */
}

/*
@Future = só pode agendar datas no futuro, não pode do passado
 */
