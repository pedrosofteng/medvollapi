package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DetalhamentoConsultaDTO(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data,
        Especialidade especialidade) {
    public DetalhamentoConsultaDTO(Consulta consulta) {
        this(
                consulta.getId(),
                consulta.getMedico().getId(),
                consulta.getPaciente().getId(),
                consulta.getData(),
                consulta.getMedico().getEspecialidade());
    }
}
