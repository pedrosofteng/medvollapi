package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
    SELECT p.ativo
    FROM Paciente p
    WHERE p.id = :idPaciente
    """)
    boolean findAtivoById(@NotNull Long idPaciente);
    /*
    boolean = pra quando você tem certeza que não vir null
    Boolean = pra quando não sabe
     */
}
