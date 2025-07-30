package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

//@Component = componente genérico
@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {
    // paciente tem que ter uma distância de 30 minutos para marcar uma consulta, não pode marcar pra daqui 15 min

    public void validar(AgendamentoConsultaDTO dados) {
        LocalDateTime dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferençaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();
        // Duration.between calcula a diferença de datas e horas, minutos, segundos etc

        if(diferençaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com atencedência mínima de 30 minutos.");
        }
    }
}
