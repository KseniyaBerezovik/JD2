package by.itacademy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@Configuration
@ComponentScan(basePackages = {"by.itacademy"})
@ContextConfiguration(classes = DatabaseConfig.class)
@Import(DatabaseConfig.class)
public class ServiceConfig {
}
