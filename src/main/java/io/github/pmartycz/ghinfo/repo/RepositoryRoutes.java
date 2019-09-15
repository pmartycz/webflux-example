package io.github.pmartycz.ghinfo.repo;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@AllArgsConstructor
class RepositoryRoutes {
    private final RepositoriesHandler handler;

    @Bean
    RouterFunction<ServerResponse> repositoryRouter() {
        return RouterFunctions.route()
                .GET("/repositories/{owner}/{name}", handler::getRepositoryByFullName)
                .GET("/repositories/{owner}", handler::getOwnerRepositories)
                .build();
    }
}
