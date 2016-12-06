package pl.beerapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties({ResourceProperties.class})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ResourceProperties resourceProperties = new ResourceProperties();

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Integer cachePeriod = resourceProperties.getCachePeriod();

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .setCachePeriod(cachePeriod);
        registry.addResourceHandler("/scripts/**")
                .addResourceLocations("classpath:/static/scripts/")
                .setCachePeriod(cachePeriod);
        registry.addResourceHandler("/styles/**")
                .addResourceLocations("classpath:/static/styles/")
                .setCachePeriod(cachePeriod);
        registry.addResourceHandler("/views/**")
                .addResourceLocations("classpath:/static/views/")
                .setCachePeriod(cachePeriod);
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("/");

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/index.html")
                .setCachePeriod(cachePeriod)
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath,
                                                   Resource location) throws IOException {
                        return location.exists() && location.isReadable() ? location
                                : null;
                    }
                });
    }

}
