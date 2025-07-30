package med.voll.api.domain.cancelarconsulta.validadores;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.cancelarconsulta.CancelamentoConsultaDTO;
import med.voll.api.domain.consulta.Consulta;
import org.springframework.stereotype.Component;

@Component
public class ValidadorHorarioCancelamento implements ValidadorCancelamentoConsulta{

    public void cancelarConsulta(CancelamentoConsultaDTO dados, Consulta consulta) {
        var consulta24HorasAntes = consulta.getData().minusHours(24);

        if(consulta24HorasAntes.isAfter(consulta.getData())) {
            throw new ValidacaoException("O cancelamento só pode ser feito com 24 horas de atencedência");
        }
    }
}
