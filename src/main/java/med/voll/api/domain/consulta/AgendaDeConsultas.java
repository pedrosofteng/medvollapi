package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public void agendar(AgendamentoConsultaDTO dados){
        if(!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
            /*
            se o id do paciente não existe, lance uma exceção com runticeexception dizendo
            "Id do paciente informado não existe!"
             */
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

//        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        /*
        como a gente não quer carregar o objeto, só queremos atribuir ele a entidade consulta
        vamos usar o getReferenceById, se precisasssemos carregar todo o objeto, usariamos o findById.get
         */
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(AgendamentoConsultaDTO dados) {
        if(dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());

        /*
        regras
        se o médico vier nulo, nós temos que escolher um médico aleatoriamente com agenda livre
        mas ele tem que vir obrigatoriamente com uma especialidade
        
        mas se não vier nulo, nós só buscamos direto.
         */
    }
}
