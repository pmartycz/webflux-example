package io.github.pmartycz.ghinfo.repo;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
final class CachedRepositories implements Repositories {
    private final Repositories repos;

    @Override
    public Mono<RepositoryModel> get(String name) {
        return repos.get(name).cache();
    }

    @Override
    public Flux<RepositoryModel> all() {
        return repos.all().cache();
    }
}
