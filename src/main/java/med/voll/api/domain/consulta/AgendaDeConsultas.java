package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    // DESGIN PATTERN > STRATEGY || SOLID
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;
    /*
    ele procura todas as classes que implementam essa interface, cria uma lista colocando cada uma dessas classes,
    e injeta cada uma delas também no projeto, não importa se você tem 1 ou 10 validadores, ele vai implementar
    todos que estejam "implements" essa interface
     */

    public DetalhamentoConsultaDTO agendar(AgendamentoConsultaDTO dados){
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

        validadores.forEach(v -> v.validar(dados));
        /*
        DESIGN PATTERN > STRATEGY || SOLID
        chamo um forEach da lista de validadores, e como todos os validadores tem o mesmo nome de método validar,
        ele vai passar um por um, passo como parâmetro os dados de AgendamentoConsultaDTO que eles tem no construtor
         */

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if(medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data.");
        }
        var consulta = new Consulta(medico, paciente, dados.data());

        consultaRepository.save(consulta);
        return new DetalhamentoConsultaDTO(consulta);
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
