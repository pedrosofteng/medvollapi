package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.medico.dto.MedicoDetalhesDTO;
import med.voll.api.domain.medico.model.Medico;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

// é uma anotação para classe exclusivas para tratar erros
@RestControllerAdvice
public class TratadorDeErros {

    // se em qualquer lugar do projeto for lançada uma exceção EntityNotFound ele vai lançar
    // essa resposta == erro 404, antes era 500
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarErro404() {
       return ResponseEntity.notFound().build();
    }

    // então toda vez que não achar algo no banco de dados, ele vai lançar um erro 404, não 500
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        // estou criando uma variável com todos os erros
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ErroValidacaoDTO::new).toList());
        // vamos criar um record para receber esses erros
        // sempre precisa do to.List() para mandar pro return
    }

    private record ErroValidacaoDTO(String campo, String mensagem) {
        public ErroValidacaoDTO(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }

        /*
        campo: "telefone"
        mensagem: "must not be blank"

        vai devolver agora o campo do erro, e a mensagem do erro somente
         */

    }

    /*
    pra poder tratar erros personalizados, com corpo personalizado, a gente recebe
    a exception no corpo como parâmetro, para verificar qual erro do Bean Validation está acontecendo

    podemos receber o erro como parâmetro em qualquer exception
     */
}
