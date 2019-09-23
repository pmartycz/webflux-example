package io.github.pmartycz.ghinfo;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

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
                .clientConnector(new ReactorClientHttpConnector(http()))
                .defaultHeader("Accept", "application/vnd.github.v3+json");

        if (properties.getUsername() != null) {
            builder.filter(
                    basicAuthentication(properties.getUsername(), properties.getPassword())
            );
        }
    }

    private HttpClient http() {
        int timeout = 500; // hmm

        return HttpClient.create()
                .followRedirect(true) // As per https://developer.github.com/v3/#http-redirects
                .compress(true) // those jsons you know they can get very long
                .tcpConfiguration(tcp -> tcp
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
                        .doOnConnected(connection -> {
                                connection.addHandlerLast(new ReadTimeoutHandler(timeout));
                                connection.addHandlerLast(new WriteTimeoutHandler(timeout));
                        })
                );
    }

}
