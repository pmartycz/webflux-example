package io.github.pmartycz.ghinfo.repo;

import lombok.Builder;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.net.URI;
import java.time.Instant;

@Value
@Builder
class RepositoryModel {
    String fullName;
    @Nullable String description;
    URI cloneUrl;
    int stars;
    Instant createdAt;
}
