package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    /*
    vou chamar o AutenticationManager para chamar o AutenticacaoService
    eu não posso chama-lo diretamente

    essa classe específica do spring ele não sabe configurar automaticamente, a gente tem que ir em SecurityConfig
    e temos que mostrar que ele tem que ler e iniciar essa classe

    precisa do @Bean lá quando a gente configurar pra ele ler, pro spring ler o método ao iniciar
     */

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<Void> efetuarLogin(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        var token = new UsernamePasswordAuthenticationToken(usuarioDTO.login(), usuarioDTO.senha());
        /* SPRING TEM O DTO DELE, não posso passar direto, pois ele espera já X dto
        sempre que criarmos um token tem que ser dessa maneira para verificar
         */

        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }

    // ResquestBody para pegar o corpo da resposta e passar para UsuarioDTO

}
