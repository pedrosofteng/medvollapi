package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;
import med.voll.api.domain.consulta.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteConsultaMesmaData implements ValidadorAngedamentoDeConsulta{
    // valida se o paciente tem consulta na mesma data, não pode

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(AgendamentoConsultaDTO dados) {
        var primeiroHorario = dados.data().withHour(7).withMinute(0);
        // estou colocando o primeiro horário da clínica, você seta com withHour, withMinute
        var ultimoHorario = dados.data().withHour(7).withMinute(0);
        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetwenn(
                dados.idPaciente(),
                primeiroHorario,
                ultimoHorario);

        if(pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia.");
        }

    }
}
