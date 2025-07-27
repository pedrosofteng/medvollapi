package med.voll.api;

import med.voll.api.config.ConfigEnv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		ConfigEnv configEnv = new ConfigEnv();
		SpringApplication.run(ApiApplication.class, args);
	}
}
