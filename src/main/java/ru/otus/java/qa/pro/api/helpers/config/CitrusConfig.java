package ru.otus.java.qa.pro.api.helpers.config;

import org.citrusframework.dsl.endpoint.CitrusEndpoints;
import org.citrusframework.http.client.HttpClient;
import org.citrusframework.ws.client.WebServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.ws.soap.SoapMessageFactory;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import javax.sql.DataSource;

@Configuration
public class CitrusConfig {

    @Bean
    public HttpClient httpClient() {
        return CitrusEndpoints.http()
                .client()
                .requestUrl("http://localhost:8080")
                .build();
    }

    @Bean
    public SoapMessageFactory messageFactory() {
        return new SaajSoapMessageFactory();
    }

    @Bean
    public WebServiceClient soapClient() {
        return CitrusEndpoints.soap()
                .client()
                .defaultUri("https://www.dataaccess.com/webservicesserver/numberconversion.wso")
                .timeout(60_000)
                .build();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:sql/init.sql'");
        dataSource.setDriverClassName("org.h2.Driver");
        return dataSource;
    }

}
