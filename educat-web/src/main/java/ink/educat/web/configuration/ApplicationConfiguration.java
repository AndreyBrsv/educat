package ink.educat.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan({
        "ink.educat.core",
        "ink.educat.user",
        "ink.educat.article",
        "ink.educat.security",
})
public class ApplicationConfiguration {
    // Пока это несколько примитивный подход, он нужен для тестов
    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-54-228-246-214.eu-west-1.compute.amazonaws.com:5432/d3c5gtjpcnehgi");
        dataSource.setUsername("pzcfiucmbrvpba");
        dataSource.setPassword("deaf1cb96055829ab03ca51f224ac922b76b15cd9a719dde3b26413325954533");
        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

}
