package io.github.pmartycz.ghinfo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.net.URI;

@Component
@ConfigurationProperties("ghinfo")
@Getter
@Setter
@Validated
public class ApplicationProperties {

    /**
     * Github API URL
     */
    private URI githubUrl = URI.create("https://api.github.com");

    /**
     * Username for Basic Authentication access to API.
     */
    @Nullable
    private String username;

    /**
     * Password for Basic Authentication access to API.
     *
     * Note: use Personal Access Token not actual password!
     */
    @Nullable
    private String password;
}
