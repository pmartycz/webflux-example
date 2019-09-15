package io.github.pmartycz.ghinfo.repo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * A collection of repositories.
 */
interface Repositories {

    /**
     * Returns information about named repository.
     */
    Mono<RepositoryModel> find(String name);

    /**
     * Returns information about all repositories in collection.
     */
    Flux<RepositoryModel> all();
}
