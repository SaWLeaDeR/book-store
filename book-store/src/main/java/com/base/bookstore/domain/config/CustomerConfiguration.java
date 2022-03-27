package com.base.bookstore.domain.config;

import com.base.bookstore.domain.config.propery.CustomerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "customer.service")
    public CustomerProperties customerProperties() {
        return new CustomerProperties();
    }
}
