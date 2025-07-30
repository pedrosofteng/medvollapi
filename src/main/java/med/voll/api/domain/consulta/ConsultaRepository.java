package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    // nomeclantura usando SPRING DATA JPA

    // Verifica se já existe uma consulta marcada para um médico específico em uma data e hora exatas.
    // Retorna true se existir, ou false se não houver nenhuma consulta nesse horário para o médico informado.
    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);

    boolean existsByPacienteIdAndDataBetween(
            @NotNull
            Long idPaciente,
            LocalDateTime primeiroHorario,
            LocalDateTime ultimoHorario);

    @Modifying
    @Query("DELETE FROM Consulta c WHERE c.id = :id")
    void deletarPorId(Long id);
}
