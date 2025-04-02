package edu.eci.cvds.elysium.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Cambiar el origen al necesario
                .allowedOrigins("https://cicero-hfg6f7g3echkbvcf.canadacentral-01.azurewebsites.net/")
                .allowedOrigins("https://limbo-cfgvasfjc6argbhh.canadacentral-01.azurewebsites.net/")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}