package com.pi.ati.ort.back;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableJpaRepositories("com.pi.ati.ort.back.repositories")
@OpenAPIDefinition(info=@Info(title="Backend - P.C.D. Proyecto Integrador", version="0.0.1", description="Permisos de Construcción Digitales - Backend del Proyecto Integrador de la carrera Analista Programador en la Universidad ORT Uruguay - Septiembre 2023 / Abril 2024 - Autores: Nicolas Fernandez y Sebastián Paulos", license = @License(name = "Ver Repositorios", url = "https://github.com/pi-ati-ort"), contact = @Contact(name = "Info", email = "nicolas@nicolasf.uy")))
public class BackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000", "https://pi-ati-ort-front.vercel.app").allowedMethods("*");
            }
        };
    }
}
