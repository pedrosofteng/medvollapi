package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;
import med.voll.api.domain.consulta.DetalhamentoConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity<?> agendar(@RequestBody @Valid AgendamentoConsultaDTO dados) {
        agenda.agendar(dados);
        return ResponseEntity.ok(new DetalhamentoConsultaDTO(null, null, null, null));
    }
}
