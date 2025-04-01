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
        // Cargar la URI de MongoDB desde .env o variables de sistema
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String mongoURI = dotenv.get("MONGODB_URI");
        
        // Si no está en .env, intentar desde variables del sistema
        if (mongoURI == null || mongoURI.isEmpty()) {
            mongoURI = System.getenv("MONGODB_URI");
        }
        
        // Verificar si se encontró URI válida
        if (mongoURI == null || mongoURI.isEmpty()) {
            System.out.println("⚠️ ADVERTENCIA: No se encontró MONGODB_URI en el entorno!");
            mongoURI = "mongodb://localhost:27017/elysium";
        } else {
            System.out.println("✅ MONGODB_URI encontrado!");
        }
        
        // Determinar el nombre de la base de datos según el entorno
        String dbName = dotenv.get("MONGODB_DATABASE");
        
        // Si no está en .env, intentar desde variables del sistema
        if (dbName == null || dbName.isEmpty()) {
            dbName = System.getenv("MONGODB_DATABASE");
        }
        
        // Si aún no hay un nombre de base de datos, usar valor por defecto según entorno
        if (dbName == null || dbName.isEmpty()) {
            String environment = System.getenv("APP_ENVIRONMENT");
            if ("production".equals(environment)) {
                dbName = "ReservaECILimbo"; // Base de datos de producción
            } else {
                dbName = "ReservaECIHades"; // Base de datos de desarrollo (por defecto)
            }
            System.out.println("⚠️ Usando base de datos por defecto según entorno: " + dbName);
        } else {
            System.out.println("✅ Nombre de base de datos encontrado en configuración: " + dbName);
        }
        
        // Limpiar la URI de MongoDB (eliminar espacios y comillas si existen)
        mongoURI = mongoURI.trim();
        if (mongoURI.startsWith("\"") && mongoURI.endsWith("\"")) {
            mongoURI = mongoURI.substring(1, mongoURI.length() - 1);
        }
        
        System.out.println("Conectando a MongoDB con URI: " + 
                        mongoURI.replaceAll(":[^:@]+@", ":****@"));
        System.out.println("Usando base de datos: " + dbName);
        
        return new SimpleMongoClientDatabaseFactory(MongoClients.create(mongoURI), dbName);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDatabaseFactory());
    }
}