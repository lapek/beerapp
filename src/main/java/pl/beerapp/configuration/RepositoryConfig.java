package pl.beerapp.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "pl.beerapp.repositories")
@EnableAutoConfiguration
@EntityScan(basePackages = {"pl.beerapp.entities"})
public class RepositoryConfig {
}
