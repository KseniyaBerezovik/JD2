package by.itacademy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

@Configuration
@ComponentScan(basePackages = {"by.itacademy.service"})
@ContextConfiguration(classes = DatabaseConfig.class)
@Import(DatabaseConfig.class)
public class ServiceConfig {
}
