package org.pupa.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class DataBaseConfig {

    private Environment env;

    @Autowired
    public DataBaseConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource bds = new DriverManagerDataSource();
        bds.setDriverClassName("org.postgresql.Driver");
        bds.setUrl(env.getProperty("spring.datasource.url"));
        bds.setUsername(env.getProperty("spring.datasource.username"));
        bds.setPassword(env.getProperty("spring.datasource.password"));

        return bds;
    }
}
