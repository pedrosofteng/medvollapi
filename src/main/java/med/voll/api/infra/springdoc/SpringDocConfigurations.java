package med.voll.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }

    /*
    na página da documentação do spring doc, ele vai criar lá um cabeçalho para passar o bearer key da JWT
    o token que tem que ir no cabeçalho da requisição, esse método cria essa funcionalidade de cabeçalho bearer
    mas para isso eu tenho que passar o

    @SecurityRequirement(name = "bearer-key")

    em todos os controllers
     */
}
