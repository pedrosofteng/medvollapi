package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;
import med.voll.api.domain.paciente.PacienteRepository;

public class ValidadorPacienteAtivo {
    // valida se o paciente está ativo

    private PacienteRepository pacienteRepository;

    public void validar(AgendamentoConsultaDTO dados) {
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído.");
        }
    }
}
