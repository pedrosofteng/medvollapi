package med.voll.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// enablewebsecurity indica que vamos personalizar as configurações de segurança
// sempre que precisar que o spring carregue a classe, tem que ter anotação
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    /*
    a gente vai sair do processo statefull para stateless
    vamos tirar a configuração aqui do login e senha, liberou as requisições
     */

    /*
    🛠 Quando usar @Bean?
    BEAN devolve um retorno para o SPRING, para por ex mudar as configurações
    Quando você quer registrar uma classe de fora do seu projeto, como um bean de biblioteca externa.
    Quando precisa de lógica de criação personalizada (parâmetros, condições, etc).
    Quando quer configurar beans mais finamente (como datasources, caches, serviços, etc).
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // SecurityFilterChain é usado para configurar coisas relacionadas com processo de autenticação e autorização
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
        /*
        cross-site- request forgery
        estamos desabilitando esse modo de segurança contra request forgery, pois o token já é
        uma forma de proteção contra esse tipo de ataque cibernético
        ELE PRECISA DE UMA EXCEPTION, throws Exception
         */

        /*
        ALTERAR DE STATEFULL PARA STATELESS -> usar tokens
        sessionManagement abre pra ver como vai ser o gerenciamento da sessão
        sessionCreationPolicy política de criação de sessão
         */
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

        /*
        essa classe AuthenticationConfiguration tem um método que CRIA AuthenticationManager
        AuthenticationManager funciona pra usar os Service de Autenticacao -> AutenticacaoService
        ele precisa de uma exception
         */
    }

    /* CONFIGURANDO O BCRYPT */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
