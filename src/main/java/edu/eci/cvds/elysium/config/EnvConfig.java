package edu.eci.cvds.elysium.config;

import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;

@Configuration
public class EnvConfig {
    @PostConstruct
    public void init() {
        Dotenv dotenv = Dotenv.configure()
                        .ignoreIfMissing() // No falla si no encuentra el archivo .env
                        .load();
        
        // Opcional: Puedes imprimir un mensaje para verificar que cargó correctamente
        System.out.println("Variables de entorno cargadas desde .env");
    }
}