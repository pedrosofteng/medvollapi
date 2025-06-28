package med.voll.api.controller;

import med.voll.api.domain.usuario.dto.UsuarioDTO;
import med.voll.api.domain.usuario.model.Usuario;
import med.voll.api.repository.UsuarioRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

// para dizer para o spring MVC que esse é um controler é @Controller
// mas como é API Rest, RestController
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    // vamos transformar em hash através de uma classe DO SPRING, que usa Bcrypt, não precisa
    // instalar a dependência bcrypt

    @Autowired
    private UsuarioRepository repository;

    // chegou uma requisição para /hello e ela é tipo GET? chame o olaMundo();
    @GetMapping
    public String olaMundo() {
        return "Hello World!";
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarAdm(@RequestBody UsuarioDTO usuario) {
        var hash = passwordEncoder.encode(usuario.senha());
        repository.save(new Usuario(usuario.login(), hash));
        return ResponseEntity.ok().build();
    }
}
