package io.github.pmartycz.ghinfo.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepositoryRoutesTest {

    @Autowired
    private WebTestClient web;

    @Test
    public void testOwnerNameRoute() {
        web.get().uri("/repositories/{owner}/{name}", "pmartycz", "apoz")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.fullName").isEqualTo("pmartycz/apoz")
                .jsonPath("$.description").hasJsonPath()
                .jsonPath("$.cloneUrl")
                        .isEqualTo("https://github.com/pmartycz/apoz.git")
                .jsonPath("$.createdAt").isEqualTo("2017-02-08T17:10:12Z")
                .jsonPath("$.stars").isNumber();
    }

    @Test
    public void testOwnerRoute() {
        web.get().uri("/repositories/{owner}", "pmartycz")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[*].fullName").isArray()
                .jsonPath("$[*].description").isArray()
                .jsonPath("$[*].cloneUrl").isArray()
                .jsonPath("$[*].createdAt").isArray()
                .jsonPath("$[*].stars").isArray();
    }

}
