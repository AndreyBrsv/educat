package ink.educat.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ink.educat.user")
public class EducatDaoConfiguration {
    // Здесь организуем пул соединений к базе
}
