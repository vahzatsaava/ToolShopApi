package com.example.toolshopapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "ToolShop",
                version = "1.1.0",
                description = "",
                contact = @Contact(
                        name = "Vazha Tsaava",
                        email = "vazhatsaava@gmail.com"
                )
        )
)
@SecurityScheme(name = "bearerAuth",
        description = "JwtAuth Description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class ToolShopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolShopApiApplication.class, args);
    }

}
