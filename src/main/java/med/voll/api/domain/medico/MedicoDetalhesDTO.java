package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;

public record MedicoDetalhesDTO(
        Long id,

        String nome,

        String email,

        String crm,

        String telefone,

        Boolean ativo,

        Especialidade especialidade,

        Endereco endereco) {

    public MedicoDetalhesDTO(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getTelefone(),
                medico.getAtivo(),
                medico.getEspecialidade(),
                medico.getEndereco()
        );
    }
}
