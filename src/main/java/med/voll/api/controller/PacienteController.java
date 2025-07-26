package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public ResponseEntity<?> listarPacientes(@PageableDefault(page = 0, size = 10, sort = "nome") Pageable paginacao) {
        Page<PacienteDetalhesDTO> page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PacienteCadastroDTO dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dados);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new PacienteDetalhesDTO(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> alterar(@RequestBody @Valid PacienteAtualizacaoDTO dados) {

    }
}
