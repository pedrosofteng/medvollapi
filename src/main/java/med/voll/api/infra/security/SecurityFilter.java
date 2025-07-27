package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


// Component é para o spring carregar a classe, mas ela é genérica, não é service, configuration e etc
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        // QUEM GUARDA O TOKEN É O FRONT END, VAMOS RECUPERAR ELE
        if(tokenJWT != null) {
            var loginToken = tokenService.getSubjetc(tokenJWT);
            /*
            SE TIVER TOKEN NO HEADER, pegue o subject
            agora se não tiver, pode continuar

            agora vou forçar pro spring, mostrar que o usuário é válido
            se chegou até aqui o TOKEN ESTÁ VALIDO, vamos regastar os dados do usuário agora
             */

            var usuario = repository.findByLogin(loginToken);
            var authentication = new UsernamePasswordAuthenticationToken
                    (usuario, null, usuario.getAuthorities());
            // esse metodo cria uma variavel authetication para passarmos pro spring, que o usuario esta autenticado

            //getAuthorities é um metodo de usuarios, so conferir la

            SecurityContextHolder.getContext().setAuthentication(authentication);
            // estamos sinalizando pro spring que o usuário está autenticado, esse método faz isso

        }

        filterChain.doFilter(request, response);
        /*
        para chamar para o próximo filtro, precisa desse linha de cima, se não ele não passa pra frente
         */
    }
    /*
    estamos herdando de uma classe do spring, essa classe implementa a interface filter(jakarta)
    ela funciona para usar a classe como um grande filtro do spring
     */

//    private String recuperarToken(HttpServletRequest request) {
//        /*
//         NO HEADER "CABEÇA" vem o token com as requisições do front end
//         estamos regastando ele com getHeader, se não tiver nada no cabeçalho vamos lançar uma exception
//         BACK END GERA E MANDA O TOKEN, o front end que guarda e envia novamente em requisições
//         requisição get? qual o token?
//         */
//        var authorizationHeader = request.getHeader("Authorization");
//        if (authorizationHeader == null) {
//            throw new RuntimeException("Token não enviado no cabelo Authorization");
//            // ATENÇÃO!!!!!
//            /*
//            QUANDO DAMOS UM THROW NEW, ele encerra a execução e manda código 500
//            ou seja se não tiver token agora, todas as requisições estarão desabilitadas
//            precisa de um token pra qualquer consulta agora
//             */
//        }
//
//        return authorizationHeader.replace("Bearer ", "");
//        // por padrão antes do token vem escrito "Bearer ", vamos tirar isso
//    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        // SE O CABEÇALHO NÃO TIVER NADA, RETORNE NULL

        return null;
    }
}