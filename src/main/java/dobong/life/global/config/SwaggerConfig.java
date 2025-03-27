package dobong.life.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("BearerAuth", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer") // eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZWxsbzMiLCJhdXRoIjoiVVNFUl9SRUdVTEFSIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTc0MzA1NzY0MCwiZXhwIjoxNzQzMTU3NjQwfQ.N1g7Epkac73jxU_5svRmpPZQIrbqROWTGRHNr-AfP5Q
                        .bearerFormat("JWT")))
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("도봉라이프 API")
                .description("도봉구 경제 활성화를 위한 관광 가이드")
                .version("1.0.0");
    }
}
