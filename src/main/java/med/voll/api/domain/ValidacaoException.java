package med.voll.api.domain;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String mensagem) {
        super(mensagem);
        /*
        Você está recebendo a mensagem no construtor, mas não está passando ela para a superclasse RuntimeException.
        Por isso, quando você lança essa exceção, o metodo getMessage() retorna null — causando a mensagem de erro
        "med.voll.api.domain.ValidacaoException: null".
         */
    }
}
