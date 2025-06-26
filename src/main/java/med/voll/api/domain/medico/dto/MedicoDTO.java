package med.voll.api.domain.medico.dto;

import med.voll.api.domain.medico.model.Especialidade;
import med.voll.api.domain.medico.model.Medico;

public record MedicoDTO(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public MedicoDTO(Medico medico) {
        this(medico.getId(),
        medico.getNome(),
        medico.getEmail(),
        medico.getCrm(),
        medico.getEspecialidade()
        );
    }
}
