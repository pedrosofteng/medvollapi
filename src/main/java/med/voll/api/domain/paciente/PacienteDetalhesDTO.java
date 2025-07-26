package med.voll.api.domain.paciente;

public record PacienteDetalhesDTO(
        Long id,
        String nome,
        String email,
        String cpf
) {
    public PacienteDetalhesDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
