package io.github.pmartycz.ghinfo.repo;

import lombok.AllArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repositories owned by specific user.
 */
@AllArgsConstructor
final class OwnerRepositories implements Repositories {
    private final WebClient web;
    private final String owner;

    @Override
    public Mono<RepositoryModel> get(String name) {
        return web.get().uri("/repos/{owner}/{name}", owner, name)
                .retrieve()
                .bodyToMono(RepositoryClientModel.class)
                .map(RepositoryMappings.INSTANCE::clientToInternal);
    }

    @Override
    public Flux<RepositoryModel> all() {
        return web.get().uri("/users/{owner}/repos", owner)
                .retrieve()
                .bodyToFlux(RepositoryClientModel.class)
                .map(RepositoryMappings.INSTANCE::clientToInternal);
    }
}
