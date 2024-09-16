package ru.otus.java.qa.pro.api.helpers;

import org.citrusframework.TestActionRunner;
import org.citrusframework.annotations.CitrusEndpoint;
import org.citrusframework.annotations.CitrusResource;
import org.citrusframework.annotations.CitrusTest;
import org.citrusframework.config.CitrusSpringConfig;
import org.citrusframework.junit.jupiter.spring.CitrusSpringSupport;
import org.citrusframework.spi.Resources;
import org.citrusframework.ws.client.WebServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.java.qa.pro.api.helpers.config.CitrusConfig;

import static org.citrusframework.ws.actions.SoapActionBuilder.soap;

@CitrusSpringSupport
@ContextConfiguration(classes = {CitrusSpringConfig.class, CitrusConfig.class})
public class SoapHelperTest {

    @CitrusEndpoint
    private WebServiceClient soapClient;

    @Test
    @CitrusTest
    public void numberToDollars(@CitrusResource TestActionRunner action) {
        action.$(soap()
                .client(soapClient)
                .send()
                .message()
                .body(new Resources.ClasspathResource("soap/soap-request1.xml"))
        );

        action.$(soap()
                .client(soapClient)
                .receive()
                .message()
                .body(new Resources.ClasspathResource("soap/soap-response1.xml"))
        );
    }

}
