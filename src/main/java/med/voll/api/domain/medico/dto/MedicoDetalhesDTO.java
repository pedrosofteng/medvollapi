package med.voll.api.domain.medico.dto;

import med.voll.api.domain.medico.model.Endereco;
import med.voll.api.domain.medico.model.Especialidade;
import med.voll.api.domain.medico.model.Medico;

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
