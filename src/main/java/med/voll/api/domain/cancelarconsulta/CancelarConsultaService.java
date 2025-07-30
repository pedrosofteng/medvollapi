package med.voll.api.domain.cancelarconsulta;

import jakarta.validation.Valid;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.cancelarconsulta.validadores.ValidadorCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancelarConsultaService {

    @Autowired
    private CancelamentoConsultaRepository cancelamentoConsultaRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private List<ValidadorCancelamentoConsulta> validador;

    public CancelamentoConsultaDetalhesDTO cancelar(@Valid CancelamentoConsultaDTO dados) {
        if(dados.idConsulta() == null) {
            throw new ValidacaoException("Id da consulta não pode estar vázio.");
        }

        if(dados.motivoCancelamento().isEmpty()) {
            throw new ValidacaoException("O motivo do cancelamento não pode estar vázio.");
        }
        var consulta = consultaRepository.findById(dados.idConsulta()).get();

        validador.forEach(v -> v.cancelarConsulta(dados, consulta));
        var cancelamentoConsulta = new CancelamentoConsulta(consulta, dados.motivoCancelamento());

        cancelamentoConsultaRepository.save(cancelamentoConsulta);

        return new CancelamentoConsultaDetalhesDTO(cancelamentoConsulta);
    }
}
