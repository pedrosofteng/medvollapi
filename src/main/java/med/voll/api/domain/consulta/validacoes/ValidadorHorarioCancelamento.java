package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.CancelamentoConsultaDTO;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class ValidadorHorarioCancelamento implements ValidadorCancelamentoConsulta{

    public void cancelarConsulta(CancelamentoConsultaDTO dados, Consulta consulta) {
        var consulta24HorasAntes = consulta.getData().minusHours(24);

        if(consulta24HorasAntes.isAfter(LocalDateTime.now())) {
            throw new ValidacaoException("O cancelamento só pode ser feito com 24 horas de atencedência");
        }
    }
}
