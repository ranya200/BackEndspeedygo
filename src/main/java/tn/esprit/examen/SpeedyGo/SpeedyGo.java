package tn.esprit.examen.SpeedyGo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@EnableAspectJAutoProxy
@EnableScheduling

@OpenAPIDefinition(
        info = @Info(
                title = "SpeedyGo API",
                version = "1.0",
                description = "Navigators Team API"
        ),
        servers = {
                @Server(url = "http://localhost:8089/speedygo", description = "Local Development Server")
        }
)

@SpringBootApplication
public class SpeedyGo {

    public static void main(String[] args) {
        SpringApplication.run(SpeedyGo.class, args);
    }

}
