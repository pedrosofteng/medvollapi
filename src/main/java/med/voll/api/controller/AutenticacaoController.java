package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.UsuarioDTO;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.TokenDTO;
import med.voll.api.infra.security.TokenService;
import med.voll.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository repository;

    /*
    vou chamar o AutenticationManager para chamar o AutenticacaoService
    eu não posso chama-lo diretamente

    essa classe específica do spring ele não sabe configurar automaticamente, a gente tem que ir em SecurityConfig
    e temos que mostrar que ele tem que ler e iniciar essa classe

    precisa do @Bean lá quando a gente configurar pra ele ler, pro spring ler o método ao iniciar
     */

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(usuarioDTO.login(), usuarioDTO.senha());
            var authentication = authenticationManager.authenticate(authenticationToken);
            /*
            se falhar o authenticationManager ele devolve 403, e já para a requisição aqui

            SPRING TEM O DTO DELE, não posso passar direto, pois ele espera já X dto
            sempre que criarmos um token tem que ser dessa maneira para verificar
            */

            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

            return ResponseEntity.ok(new TokenDTO(tokenJWT));
            // tudo que chega/sai da API tem um DTO

            /*
            authentication.getPrincipal() -> pega o login, username, usuário
            (Usuario) faz um casting, porque authentication.getPrincipal() gera um objetc
            então ele converte para usuário
            nosso gerarToken precisa de um um
             */
        } catch (BadCredentialsException e) {
            System.out.println("Credenciais inválidas!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senha incorretos.");
            /*
            se der erro de credenciais inválidas ele vai me alertar, use BadCredentialsException
             */
        } catch (Exception e) {
            System.out.println("Erro desconhecido: " + e.getMessage());
            e.printStackTrace();
            // vai listar qualquer mensagem de erro que vier
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no login.");
            // esse try catch vai listar qualquer outra mensagem de erro que possa vir
        }
    }

    // ResquestBody para pegar o corpo da resposta e passar para UsuarioDTO

    @PostMapping("/criar")
    public ResponseEntity<?> criarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        var usuario = new Usuario(usuarioDTO.login(), passwordEncoder.encode(usuarioDTO.senha()));
        repository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

}
