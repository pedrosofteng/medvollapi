package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.cancelarconsulta.CancelamentoConsultaDTO;
import med.voll.api.domain.cancelarconsulta.CancelarConsultaService;
import med.voll.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @Autowired
    private CancelarConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> agendar(@RequestBody @Valid AgendamentoConsultaDTO dados) {
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/cancelar")
    @Transactional
    public ResponseEntity<?> cancelar(@RequestBody @Valid CancelamentoConsultaDTO dados) {
        var dto = consultaService.cancelar(dados);
        return ResponseEntity.ok(dto);
    }
}
