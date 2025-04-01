package edu.eci.cvds.elysium.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClients;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
@EnableMongoRepositories(basePackages = "edu.eci.cvds.elysium.repository")
public class MongoConfig {

    @Bean
public MongoDatabaseFactory mongoDatabaseFactory() {
    // Cargar la URI de MongoDB desde .env
    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    String mongoURI = dotenv.get("MONGODB_URI");
    
    if (mongoURI == null || mongoURI.isEmpty()) {
        System.out.println("⚠️ ADVERTENCIA: No se encontró MONGODB_URI en el archivo .env!");
        mongoURI = "mongodb://localhost:27017/elysium";
    } else {
        System.out.println("✅ MONGODB_URI encontrado en .env!");
    }
    
    System.out.println("Conectando a MongoDB con URI: " + 
                    mongoURI.replaceAll(":[^:@]+@", ":****@"));
    
    return new SimpleMongoClientDatabaseFactory(MongoClients.create(mongoURI), "ReservaECI");
}

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDatabaseFactory());
    }
}