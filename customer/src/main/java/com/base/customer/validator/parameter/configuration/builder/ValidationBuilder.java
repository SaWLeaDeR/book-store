package com.base.customer.validator.parameter.configuration.builder;

import com.base.customer.model.error.CustomerError;
import com.base.error.ErrorFactory;
import com.base.error.ErrorMarker;
import com.base.error.model.ErrorEnum;
import com.base.error.model.ServiceError;

import java.util.Optional;

public class ValidationBuilder {

    private ValidationBuilder() {
    }

    public static boolean nameIsEmpty(String name) {
        return null == name;
    }

    public static boolean nameSizeExceeds(String name) {
        return name.length() > 100;
    }

    public static Optional<ServiceError> buildError(ErrorMarker errorMarker, String errorMessage) {
        return Optional.of(
            ErrorFactory.fromErrorToken(
                    errorMarker.getErrorToken(CustomerError.TOP_ERROR_DOMAIN, errorMessage))
                .withErrorMarker(ErrorEnum.REQUEST_NOT_VERIFIED));
    }
}
