package pl.beerapp.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.beerapp.controllers.RecipeController;
import pl.beerapp.security.AuthFilter;

@Configurable
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // @formatter:off
        web.ignoring()
                .antMatchers(
                        "/",
                        "/index.html",
                        "/home",
                        "/webjars/**",
                        "/images/**",
                        "/scripts/**",
                        "/styles/**",
                        "/views/**",
                        "/auth/**",
                        "/favicon.ico"
                );
        // @formatter:on
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore(new AuthFilter(), UsernamePasswordAuthenticationFilter.class);
        // @formatter:on
    }
}
