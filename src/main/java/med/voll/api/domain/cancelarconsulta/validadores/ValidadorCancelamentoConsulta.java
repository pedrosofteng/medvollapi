package med.voll.api.domain.cancelarconsulta.validadores;

import med.voll.api.domain.cancelarconsulta.CancelamentoConsultaDTO;
import med.voll.api.domain.consulta.Consulta;

public interface ValidadorCancelamentoConsulta {

    void cancelarConsulta(CancelamentoConsultaDTO dados, Consulta consulta);
}
