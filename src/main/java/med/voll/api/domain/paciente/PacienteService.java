package med.voll.api.domain.paciente;

import med.voll.api.repository.PacienteRepository;
import med.voll.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public Page<PacienteDetalhesDTO> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(PacienteDetalhesDTO::new);
    }
}
