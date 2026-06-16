package com.ai_app_be.services;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.ai_app_be.App;
import com.ai_app_be.configuration.AppConfig;

@SpringBootTest(
        classes = App.class,
        properties = {
                "spring.datasource.url=jdbc:h2:mem:service-test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
                "spring.datasource.driver-class-name=org.h2.Driver",
                "spring.datasource.username=sa",
                "spring.datasource.password=",
                "spring.jpa.hibernate.ddl-auto=create-drop"
        }
)
@Import(AppConfig.class)
public abstract class BaseServiceTest {
}
