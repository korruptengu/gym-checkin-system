package com.korruptengu.gymcheckinsystem;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
        info = @Info(
                title = "Gym Check-in System API",
                version = "1.0",
                description = "API-Dokumentation für das Fitnessstudio-Management-System"
        )
)
@SpringBootApplication
public class GymCheckinSystemApplication {
    public static void main(String[] args) {
            SpringApplication.run(GymCheckinSystemApplication.class, args);
        }

}
