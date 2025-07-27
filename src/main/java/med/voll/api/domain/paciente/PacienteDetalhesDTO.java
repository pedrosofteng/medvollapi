package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.endereco.EnderecoDTO;

public record PacienteDetalhesDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco
) {
    public PacienteDetalhesDTO(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getCpf(),
                paciente.getEndereco());
    }
}
