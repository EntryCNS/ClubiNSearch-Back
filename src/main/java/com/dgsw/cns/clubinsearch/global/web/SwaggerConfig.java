
package com.dgsw.cns.clubinsearch.global.web;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Club iN Search")
                .version("version 1.0")
                .description("Club iN Search API Docs");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
