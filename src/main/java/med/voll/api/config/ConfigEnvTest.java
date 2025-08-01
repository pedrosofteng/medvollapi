package med.voll.api.config;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigEnvTest {

    public ConfigEnvTest() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("DB_USER", dotenv.get("DB_USER"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        System.setProperty("DB_NAME", dotenv.get("DB_NAME_TEST"));
        System.setProperty("DB_HOST", dotenv.get("DB_HOST"));
    }
}
