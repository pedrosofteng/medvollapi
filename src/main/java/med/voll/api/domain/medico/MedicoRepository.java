package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            SELECT m FROM Medico m
            WHERE
            m.ativo = 1
            AND
            m.especialidade = :especialidade
            AND
            m.id NOT IN(
                SELECT c.medico.id FROM Consulta c
                WHERE
                c.data = :data
            )
            ORDER BY RAND()
            LIMIT 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, @NotNull @Future LocalDateTime data);
    // m.ativo = 1 mesma coisa de m.ativo = true, mas o 1 dá mais certeza
    /*
    selecione a tabela médicos
    onde
    esteja ativos
    e
    seja dessa especialidade do parâmetro do método
    e
    não esteja
    com hora marcada na entidade Consulta
    ordene de forma aleatória
    e limite 1 a busca
     */

    @Query("""
            SELECT m.ativo
            FROM Medico m
            WHERE m.id = :idMedico
            """)
    Boolean findAtivoById(Long idMedico);
}
