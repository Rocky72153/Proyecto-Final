package co.edu.elbosque.procureit.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI procureItOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ProcureIT API")
                        .description("API REST para gestión de solicitudes, aprobaciones, catálogo y cumplimiento")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación Swagger UI")
                        .url("/swagger-ui.html"));
    }
}
