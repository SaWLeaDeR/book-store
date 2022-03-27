package com.base.customer.validator.configuration;

import com.base.customer.validator.CustomerRequestValidator;
import com.base.customer.validator.model.enumeration.CustomerRequestType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class CustomerRequestValidatorConfiguration {

    @Bean
    public Map<CustomerRequestType, CustomerRequestValidator> customerRequestValidatorMap(Collection<CustomerRequestValidator> validators) {
        return validators.stream()
            .collect(Collectors.toMap(
                CustomerRequestValidator::getValidatorType,
                Function.identity()
            ));
    }
}
