package med.voll.api;

import med.voll.api.config.ConfigEnv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/*
Isso fará com que o Spring serialize a Page como um DTO estável com os campos content, page, size, totalElements, etc.
 */
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		ConfigEnv configEnv = new ConfigEnv();
		SpringApplication.run(ApiApplication.class, args);
	}
}
