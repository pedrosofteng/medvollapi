package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.AgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsulta {
    // DESGIN PATTERN > STRATEGY || SOLID

    void validar(AgendamentoConsultaDTO dados);

    /*
    estamos injetando uma interface em todos os validadores pois eles tem os mesmos métodos chamado "validar"
    interface o spring carrega automaticamente no projeto, não precisa injetar
    @Component, @Service, @RestController, @Configuration
     */
}
