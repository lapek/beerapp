package pl.beerapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan("pl.beerapp")
@SpringBootApplication
public class HomebrewingAppApplication {

    private static final Logger log = LoggerFactory.getLogger(HomebrewingAppApplication.class);

    private Environment env;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(HomebrewingAppApplication.class);

        Environment env = app.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t"
                + "Application is running!" +
                "\n----------------------------------------------------------");
    }
}
