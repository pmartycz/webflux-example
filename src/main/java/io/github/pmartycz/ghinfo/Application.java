package io.github.pmartycz.ghinfo;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@SpringBootApplication
@AllArgsConstructor
public class Application implements WebClientCustomizer {
    private final ApplicationProperties properties;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Customize Spring Boot provided WebClient.Builder
     * for work with Github API.
     */
    @Override
    public void customize(WebClient.Builder builder) {
        builder
                .baseUrl(properties.getGithubUrl().toString())
                .defaultHeader("Accept", "application/vnd.github.v3+json");

        if (properties.getUsername() != null) {
            builder.filter(
                    basicAuthentication(properties.getUsername(), properties.getPassword())
            );
        }
    }

}
