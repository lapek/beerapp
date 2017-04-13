package pl.beerapp.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "pl.beerapp.repositories")
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan(basePackages = {"pl.beerapp.entities"})
public class RepositoryConfig {

}