package es.ebde.ap01.calendar.shared.exception.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class ExceptionConfiguration {

    @Bean
    public ResourceBundleMessageSource errorMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("errors/errors");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultLocale(java.util.Locale.forLanguageTag("es"));
        messageSource.setCacheSeconds(3600);
        return messageSource;
    }
}

