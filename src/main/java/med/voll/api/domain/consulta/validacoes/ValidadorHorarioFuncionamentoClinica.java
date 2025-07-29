package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ValidadorHorarioFuncionamentoClinica {
    // valida se está dentro do horário de funcionamento da clínica

    public void validar(AgendamentoConsultaDTO dados) {
        LocalDateTime dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAbertura = dataConsulta.getHour() < 7;
        var depoisDoEncerramento = dataConsulta.getHour() > 18;
        if(domingo || antesDaAbertura || depoisDoEncerramento) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }

        /*
        var domingo = getDayOfWeek pega o dia da semana de dataConsulta e faz um .equals com DOMINGO
        var antes = faz uma comparação com a hora atual, se ela for menor do que 7, deixa true
        var depois = compara hora atual, se ela for maior do que 18, deixa true

        o if verifica se algum é true, se algum for, ESTÁ ERRADO, então lança uma exceção
         */
    }
}
