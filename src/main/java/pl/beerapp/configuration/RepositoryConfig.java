package pl.beerapp.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;

@Configuration
@EnableJpaRepositories(basePackages = "pl.beerapp.repositories")
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan(basePackages = {"pl.beerapp.entities"})
public class RepositoryConfig {

//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
//        dataSource.setUrl("jdbc:hsqldb:mem:testdb");
//        dataSource.setUsername("sa");
//        dataSource.setPassword("");
//        return dataSource;
//    }
//
//    @Bean
//    public EntityManager entityManager() {
//        return entityManagerFactory().getObject().createEntityManager();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan("package.where.your.entites.like.CustSys.are.stored");
//        return em;
//    }

}
