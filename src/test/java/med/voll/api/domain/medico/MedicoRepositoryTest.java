package med.voll.api.domain.medico;

import med.voll.api.config.ConfigEnv;
import med.voll.api.config.ConfigEnvTest;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.EnderecoDTO;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteCadastroDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// Essa anotação é usada para testar uma interface Repository JPA
@DataJpaTest
/*
nessa anotação ele vai tirar o banco de memórias rapido, e vai pegar o banco de dados tradicional do nosso projeto
em disco, mysql, postgre, ele será mais lento mas vai ser um teste mais fiel, ele vai usar o mesmo banco de dados
que o nosso projeto
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeAll
    static void setupEnv(){
        ConfigEnvTest configEnvTest = new ConfigEnvTest();
    }
    // carrega isso antes de tudo do teste

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        // cria uma data sempre na segunda feira as 10

        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
        var consulta = cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
        assertThat(medicoLivre).isNull();
    }

    private Consulta cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        var consulta = new Consulta(null, medico, paciente, data, true);
        testEntityManager.persist(consulta);
        return consulta;
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(medicoCadastroDTO(nome, email, crm, especialidade));
        testEntityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(pacienteCadastroDTO(nome, email, cpf));
        testEntityManager.persist(paciente);
        return paciente;
    }

    private MedicoCadastroDTO medicoCadastroDTO(String nome, String email, String crm, Especialidade especialidade) {
        return new MedicoCadastroDTO(
                nome,
                email,
                crm,
                especialidade,
                enderecoDTO(),
                "99123456789"
        );
    }

    private PacienteCadastroDTO pacienteCadastroDTO(String nome, String email, String cpf) {
        return new PacienteCadastroDTO(
                nome,
                email,
                "99123456789",
                cpf,
                enderecoDTO()
        );
    }

    private EnderecoDTO enderecoDTO() {
        return new EnderecoDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}

/*
ARQUIVO MOCKITO PARA COLOCAR NO POM.XML

<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-surefire-plugin</artifactId>
  <version>3.1.2</version>
  <configuration>
    <argLine>
      -javaagent:${settings.localRepository}/net/bytebuddy/byte-buddy-agent/1.17.5/byte-buddy-agent-1.17.5.jar
    </argLine>
  </configuration>
</plugin>

 */