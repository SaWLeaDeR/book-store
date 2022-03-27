package com.base.customer.validator.parameter.configuration;

import com.base.customer.model.error.CustomerError;
import com.base.customer.validator.parameter.configuration.builder.ValidationBuilder;
import com.base.error.ErrorMarker;
import com.base.error.model.ServiceError;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Configuration
public class CustomerParameterConfiguration {

    @Bean("nameParameterValidations")
    public List<Function<String, Optional<ServiceError>>> nameParameterValidations(ErrorMarker errorMarker) {
        return Arrays.asList(name -> ValidationBuilder.nameIsEmpty(name) ?
                ValidationBuilder.buildError(errorMarker, CustomerError.NAME_INVALID) : Optional.empty(),
            name -> ValidationBuilder.nameSizeExceeds(name) ?
                ValidationBuilder.buildError(errorMarker, CustomerError.NAME_SIZE_EXCEEDS) : Optional.empty());
    }

    @Bean("surnameParameterValidations")
    public List<Function<String, Optional<ServiceError>>> surnameParameterValidations(ErrorMarker errorMarker) {
        return Arrays.asList(name -> ValidationBuilder.nameIsEmpty(name) ?
                ValidationBuilder.buildError(errorMarker, CustomerError.SURNAME_INVALID) : Optional.empty(),
            name -> ValidationBuilder.nameSizeExceeds(name) ?
                ValidationBuilder.buildError(errorMarker, CustomerError.SURNAME_SIZE_EXCEEDS) : Optional.empty());
    }
}
