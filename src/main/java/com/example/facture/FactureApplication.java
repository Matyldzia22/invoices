package com.example.facture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class FactureApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FactureApplication.class);
    }

    public static void main(String[] args) {

        SpringApplication app =
                new SpringApplication(FactureApplication.class);
        app.run(args);
    }


}