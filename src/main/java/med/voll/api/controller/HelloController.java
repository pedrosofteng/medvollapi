package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// para dizer para o spring MVC que esse é um controler é @Controller
// mas como é API Rest, RestController
@RestController
@RequestMapping("/hello")
public class HelloController {

    // chegou uma requisição para /hello e ela é tipo GET? chame o olaMundo();
    @GetMapping
    public String olaMundo() {
        return "Hello World!";
    }
}
