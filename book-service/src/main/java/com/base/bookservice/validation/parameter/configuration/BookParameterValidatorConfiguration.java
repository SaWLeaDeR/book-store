package com.base.bookservice.validation.parameter.configuration;

import com.base.bookservice.model.error.BookError;
import com.base.bookservice.validation.parameter.configuration.builder.ValidationBuilder;
import com.base.error.ErrorMarker;
import com.base.error.model.ServiceError;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Configuration
public class BookParameterValidatorConfiguration {

    @Bean("nameParameterValidations")
    public List<Function<String, Optional<ServiceError>>> nameParameterValidations(ErrorMarker errorMarker) {
        return Arrays.asList(name -> ValidationBuilder.isEmpty(name) ?
                ValidationBuilder.buildError(errorMarker, BookError.NAME_INVALID) : Optional.empty(),
            name -> ValidationBuilder.nameSizeExceeds(name) ?
                ValidationBuilder.buildError(errorMarker, BookError.NAME_SIZE_EXCEEDS) : Optional.empty());
    }

    @Bean("idParameterValidations")
    public List<Function<String, Optional<ServiceError>>> idParameterValidations(ErrorMarker errorMarker) {
        return Arrays.asList(name -> ValidationBuilder.isEmpty(name) ?
                ValidationBuilder.buildError(errorMarker, BookError.ID_CAN_NOT_BE_EMPTY) : Optional.empty(),
            name -> ValidationBuilder.isHexStringEmpty(name) ?
                ValidationBuilder.buildError(errorMarker, BookError.ID_INVALID) : Optional.empty());
    }

    @Bean("sellerParameterValidations")
    public List<Function<String, Optional<ServiceError>>> sellerParameterValidations(ErrorMarker errorMarker) {
        return Arrays.asList(name -> ValidationBuilder.isEmpty(name) ?
                ValidationBuilder.buildError(errorMarker, BookError.SELLER_INVALID) : Optional.empty(),
            name -> ValidationBuilder.nameSizeExceeds(name) ?
                ValidationBuilder.buildError(errorMarker, BookError.SELLER_SIZE_EXCEEDS) : Optional.empty());
    }

    @Bean("stockParameterValidations")
    public List<Function<Integer, Optional<ServiceError>>> stockParameterValidations(ErrorMarker errorMarker) {
        return Arrays.asList(name -> ValidationBuilder.isEmpty(name) ?
                ValidationBuilder.buildError(errorMarker, BookError.STOCK_INVALID) : Optional.empty(),
            name -> ValidationBuilder.isStockSizeValid(name) ?
                ValidationBuilder.buildError(errorMarker, BookError.STOCK_SIZE_INVALID) : Optional.empty());
    }
}
