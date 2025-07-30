package med.voll.api.domain.cancelarconsulta;

import java.time.LocalDateTime;

public record CancelamentoConsultaDetalhesDTO(
        String nomeMedico,
        String nomePaciente,
        LocalDateTime data,
        String motivoCancelamento
) {
    public CancelamentoConsultaDetalhesDTO(CancelamentoConsulta cancelamentoConsulta) {
        this(
                cancelamentoConsulta.getConsulta().getMedico().getNome(),
                cancelamentoConsulta.getConsulta().getPaciente().getNome(),
                cancelamentoConsulta.getConsulta().getData(),
                cancelamentoConsulta.getMotivoCancelamento());
    }
}
