package com.softserve.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
public class ApplicationConfig {

    //@Value("${messages.basename.path}")
    private String messagesBasename = "classpath:messages/messages";
    //private String messagesBasename = "C:\\Tools\\ideaProjects\\lv560thyme\\src\\main\\resources\\messages\\messages";

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setFallbackToSystemLocale(false);
        //
        messageSource.setBasenames(messagesBasename);
        //messageSource.setBasenames("file:" + messagesBasename);
        return messageSource;
    }

}
