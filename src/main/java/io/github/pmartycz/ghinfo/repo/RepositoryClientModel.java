package io.github.pmartycz.ghinfo.repo;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.net.URI;
import java.time.Instant;

@Value
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
class RepositoryClientModel {
    int id;
    String name;
    String fullName;
    @Nullable String description;
    URI cloneUrl;
    Instant createdAt;
    int stargazersCount;
}
