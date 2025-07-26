package med.voll.api.domain.paciente;

public record PacienteDetalhesDTO(
        String nome,
        String email,
        String cpf
) {
    public PacienteDetalhesDTO(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
