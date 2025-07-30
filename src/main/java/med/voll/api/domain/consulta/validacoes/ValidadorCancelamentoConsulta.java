package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.CancelamentoConsultaDTO;
import med.voll.api.domain.consulta.Consulta;

public interface ValidadorCancelamentoConsulta {

    void cancelarConsulta(CancelamentoConsultaDTO dados, Consulta consulta);
}
