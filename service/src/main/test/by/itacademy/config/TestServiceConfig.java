package by.itacademy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(value = "by.itacademy.*")
@Import(ServiceConfig.class)
public class TestServiceConfig {
}
