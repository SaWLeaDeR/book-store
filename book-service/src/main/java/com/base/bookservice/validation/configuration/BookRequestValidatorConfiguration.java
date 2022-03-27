package com.base.bookservice.validation.configuration;

import com.base.bookservice.validation.BookRequestValidationService;
import com.base.bookservice.validation.model.enumeration.BookRequestType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class BookRequestValidatorConfiguration {

    @Bean
    public Map<BookRequestType, BookRequestValidationService> bookRequestValidationServiceMap(Collection<BookRequestValidationService> validators) {
        return validators.stream()
            .collect(Collectors.toMap(
                BookRequestValidationService::getValidatorType,
                Function.identity()
            ));
    }
}
