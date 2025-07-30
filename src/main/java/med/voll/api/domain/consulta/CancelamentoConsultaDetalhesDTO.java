package med.voll.api.domain.consulta;

public record CancelamentoConsultaDetalhesDTO(
        Consulta consulta,
        String motivoCancelamento
) {
    public CancelamentoConsultaDetalhesDTO(CancelamentoConsulta cancelamentoConsulta) {
        this(cancelamentoConsulta.getConsulta(), cancelamentoConsulta.getMotivoCancelamento());
    }
}
