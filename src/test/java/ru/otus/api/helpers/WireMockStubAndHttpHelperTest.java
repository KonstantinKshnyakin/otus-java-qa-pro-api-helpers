package ru.otus.api.helpers;

import static org.citrusframework.http.actions.HttpActionBuilder.http;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.citrusframework.TestActionRunner;
import org.citrusframework.annotations.CitrusEndpoint;
import org.citrusframework.annotations.CitrusResource;
import org.citrusframework.annotations.CitrusTest;
import org.citrusframework.config.CitrusSpringConfig;
import org.citrusframework.http.client.HttpClient;
import org.citrusframework.junit.jupiter.spring.CitrusSpringSupport;
import org.citrusframework.spi.Resources;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.api.helpers.config.CitrusConfig;

@WireMockTest(httpPort = 8080)
@CitrusSpringSupport
@ContextConfiguration(classes = {CitrusSpringConfig.class, CitrusConfig.class})
public class WireMockStubAndHttpHelperTest {

    @CitrusEndpoint
    private HttpClient httpClient;

    @Test
    @CitrusTest
    public void getUserById(@CitrusResource TestActionRunner action) {
        action.$(http()
                .client(httpClient)
                .send()
                .get("/user/get/12")
                .message()
                .accept("application/json")
        );

        action.$(http()
                        .client(httpClient)
                        .receive()
                        .response(HttpStatus.OK)
                        .message()
                        .contentType("application/json")
                        .body(new Resources.ClasspathResource("__files/http-response1.json"))
        );
    }

    @Test
    @CitrusTest
    public void courseGetAll(@CitrusResource TestActionRunner action) {
        action.$(http()
                .client(httpClient)
                .send()
                .get("/course/get/all")
                .message()
                .accept("application/json")
        );

        action.$(http()
                .client(httpClient)
                .receive()
                .response(HttpStatus.OK)
                .message()
                .contentType("application/json")
                .body(new Resources.ClasspathResource("__files/http-response2.json"))
        );
    }

    @Test
    @CitrusTest
    public void userGetAll(@CitrusResource TestActionRunner action) {
        action.$(http()
                .client(httpClient)
                .send()
                .get("/user/get/all")
                .message()
                .accept("application/json")
        );

        action.$(http()
                .client(httpClient)
                .receive()
                .response(HttpStatus.OK)
                .message()
                .contentType("application/json")
                .body(new Resources.ClasspathResource("__files/http-response3.json"))
        );
    }

}
