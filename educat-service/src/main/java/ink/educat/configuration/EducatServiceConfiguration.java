package ink.educat.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("ink.educat.service")
@Import(EducatDaoConfiguration.class)
public class EducatServiceConfiguration {

}
