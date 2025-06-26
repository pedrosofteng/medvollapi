package med.voll.api.domain.medico.service;

import med.voll.api.domain.medico.dto.MedicoDTO;
import med.voll.api.domain.medico.dto.MedicoDetalhesDTO;
import med.voll.api.domain.medico.model.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceMedico {

    @Autowired
    private MedicoRepository repository;

    public Page<MedicoDTO> converteDados(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(MedicoDTO::new);

        // não precisa de stream nem to list, page já faz isso, pageable
    }

    public ResponseEntity<MedicoDetalhesDTO> detalhar(Long id) {
        Medico medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoDetalhesDTO(medico));
    }
}
