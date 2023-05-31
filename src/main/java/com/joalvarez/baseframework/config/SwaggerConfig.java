package com.joalvarez.baseframework.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${app.name:unknown}")
    private String name;
    @Value("${app.description:unknown}")
    private String description;
    @Value("${app.version:unknown}")
    private String version;

    @Bean
    public OpenAPI api() {
        Contact contact = new Contact();
        contact.email("alvarez.joaquinez@gmail.com");
        contact.setUrl("https://github.com/joalvarezdev");
        contact.setName("joalvarez");

        Info info = new Info();

        info.setTitle(this.name);
        info.description(this.description);
        info.setVersion(this.version);
        info.setContact(contact);

        return new OpenAPI()
                .info(info);
    }
}
