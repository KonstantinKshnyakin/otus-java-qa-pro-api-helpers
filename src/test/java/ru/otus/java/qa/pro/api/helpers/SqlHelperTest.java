package ru.otus.java.qa.pro.api.helpers;

import org.citrusframework.TestActionRunner;
import org.citrusframework.annotations.CitrusResource;
import org.citrusframework.annotations.CitrusTest;
import org.citrusframework.config.CitrusSpringConfig;
import org.citrusframework.junit.jupiter.spring.CitrusSpringSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.java.qa.pro.api.helpers.config.CitrusConfig;

import javax.sql.DataSource;

import static org.citrusframework.actions.ExecuteSQLQueryAction.Builder.query;

@CitrusSpringSupport
@ContextConfiguration(classes = {CitrusSpringConfig.class, CitrusConfig.class})
public class SqlHelperTest {

    @Autowired
    private DataSource dataSource;

    @Test
    @CitrusTest
    public void sqlGetById(@CitrusResource TestActionRunner action) {
        action.$(query(dataSource)
                .statement("select count(*) as cnt from users where id = 1")
                .validate("cnt", "1")
        );
    }

    @Test
    @CitrusTest
    public void sqlGetAll(@CitrusResource TestActionRunner action) {
        action.$(query(dataSource)
                .statement("select count(*) as cnt from users")
                .validate("cnt", "3")
        );
    }

}
