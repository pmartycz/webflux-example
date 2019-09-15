package io.github.pmartycz.ghinfo.repo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
final class RepositoriesHandler {
    private final RepositoriesFactory repos;

    Mono<ServerResponse> getRepositoryByFullName(ServerRequest request) {
        String owner = request.pathVariable("owner");
        String name = request.pathVariable("name");

        return ServerResponse.ok()
                .body(repos.ownerRepositories(owner).find(name), RepositoryModel.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    Mono<ServerResponse> getOwnerRepositories(ServerRequest request) {
        String owner = request.pathVariable("owner");

        return ServerResponse.ok()
                .body(repos.ownerRepositories(owner).all(), RepositoryModel.class);
    }
}
