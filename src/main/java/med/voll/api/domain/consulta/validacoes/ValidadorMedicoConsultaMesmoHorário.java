package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;
import med.voll.api.domain.consulta.ConsultaRepository;

public class ValidadorMedicoConsultaMesmoHorário {
    // valida se o médico tem consulta no mesmo horário

    private ConsultaRepository consultaRepository;

    public void validar(AgendamentoConsultaDTO dados) {
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndData(
                dados.idMedico(),
                dados.data());

        if(medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Medico já possui outra consulta agendada nesse mesmo horário.");
        }
    }
}
