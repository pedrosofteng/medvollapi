package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public Page<PacienteDTO> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(PacienteDTO::new);
    }

    public Paciente atualizarInformacoes(@Valid PacienteAtualizacaoDTO dados) {
        Paciente paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInfo(dados);
        return paciente;
    }

    public void excluir(Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
    }

    public void salvar(Paciente paciente) {
        repository.save(paciente);
    }

    public ResponseEntity<?> detalharPaciente(Long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new PacienteDetalhesDTO(paciente));
    }
}
