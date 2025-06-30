package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.github.cdimascio.dotenv.Dotenv;
import med.voll.api.domain.usuario.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {
    Dotenv dotenv = Dotenv.load();
    private String senha = dotenv.get("DB_SENHA_TOKEN");

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(senha);
            /*
            HMAC256 -> é como vamos salvar o token, ler e modificar
            tem vários tipo de forma de salvar, no site jwt.io, tem várias versões
            consultar e estudar qual é mais eficiente e inteligente pro meu projeto

            esse metodo em especifico nos vamos criar uma senha agora entre os parenteses do HMAC256
            e ela vai servir para assinar o token, para autenticar ele, então dentro ()
            vamos criar uma senha que vamos usar futuramente, nós vamos camuflar essa senha
            pra não deixar um dado tão valioso exposto
             */

            // aqui embaixo no lugar do return era "String token ="

            return JWT.create()
                    .withIssuer("API Voll.med")
                    // assinatura do projeto, nome do software
                    .withSubject(usuario.getLogin())
                    /*
                    vamos identificar quem é o usuário, como a aplicação é Stateless
                    nós temos que conseguir localizar o usuário através do token
                     */
                    .withClaim("id", usuario.getId())
                    // vamos guardar o id do usuário no token
                    // withClaim = chave, valor
                    .withExpiresAt(dataExpiracao())
                    // vamos expirar a validade do token em quanto tempo?
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar o token.");
        }
    }

    // metodo para validar o token e retornar o subject ou "login"
    public String getSubjetc(String tokenJWT) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(senha);
            return JWT.require(algoritmo)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("TokenJWT inválido ou expirado!");
        }
    }


    /*
    O QUE É INSTANT?
    📌 Características:
    Representa um instante exato (sem fuso horário).
    Usado muito em logs, tokens JWT, auditoria, timestamps, etc.
    Armazena a data como quantidade de segundos desde a “época Unix” (1970-01-01T00:00Z).
     */
    private Instant dataExpiracao() {
        return Instant.now().plus(2, ChronoUnit.HOURS);
//        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));

        // esse debaixo controla o fuso

        /*
        aqui vou configurar o tempo que o token vai ficar válido
        Instant.now() -> o exato momento agora
        .plus(2 (horas), ChronoUnit.HOURS (representa a unidade de horas))
         */
    }
}
