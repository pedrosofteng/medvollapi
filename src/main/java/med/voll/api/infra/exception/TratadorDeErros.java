package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// é uma anotação para classe exclusivas para tratar erros
@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<?> tratarRegraDeNegocio(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

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
        /*
        var erros = ex.getFieldErrors();
        Pega todos os erros de campos (campos inválidos) que foram validados e falharam.
        ex.getFieldErrors() retorna uma lista de FieldError. Cada FieldError contém informações como o nome do campo e a mensagem de erro.
        erros.stream().map(ErroValidacaoDTO::new).toList()
        Converte a lista de FieldError para uma lista de ErroValidacaoDTO.
        O map(ErroValidacaoDTO::new) quer dizer: para cada erro, criar um novo ErroValidacaoDTO usando o construtor que recebe um FieldError
        toList() transforma o Stream de volta para uma lista normal.
         */
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
