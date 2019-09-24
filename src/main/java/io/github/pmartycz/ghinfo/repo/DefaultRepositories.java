package io.github.pmartycz.ghinfo.repo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
final class DefaultRepositories implements RepositoriesFactory {
    private final WebClient web;

    @Autowired
    DefaultRepositories(WebClient.Builder builder) {
        this(builder.clone().build());
    }

    @Override
    public Repositories ownerRepositories(String owner) {
        return new CachedRepositories(new OwnerRepositories(web, owner));
    }

}
