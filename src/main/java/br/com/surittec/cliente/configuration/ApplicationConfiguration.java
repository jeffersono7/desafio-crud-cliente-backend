package br.com.surittec.cliente.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class ApplicationConfiguration {

    @Bean
    RestTemplate restTemplateFactory() {
        return new RestTemplateBuilder().build();
    }

    @Bean
    @Scope(WebApplicationContext.SCOPE_APPLICATION)
    ObjectMapper objectMapperFactory() { return new ObjectMapper(); }
}
