package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public ResponseEntity<?> listarPacientes(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {
        Page<PacienteDTO> page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PacienteCadastroDTO dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dados);
        service.salvar(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new PacienteDetalhesDTO(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> alterar(@RequestBody @Valid PacienteAtualizacaoDTO dados) {
        var paciente = service.atualizarInformacoes(dados);
        return ResponseEntity.ok(new PacienteDetalhesDTO(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarEspecifico(@PathVariable Long id) {
        return service.detalharPaciente(id);
    }
}
