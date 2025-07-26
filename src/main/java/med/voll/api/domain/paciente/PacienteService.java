package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public Page<PacienteDetalhesDTO> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(PacienteDetalhesDTO::new);
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
}
