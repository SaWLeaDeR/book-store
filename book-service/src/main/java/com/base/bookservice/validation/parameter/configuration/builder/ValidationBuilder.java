package com.base.bookservice.validation.parameter.configuration.builder;

import com.base.bookservice.model.error.BookError;
import com.base.error.ErrorFactory;
import com.base.error.ErrorMarker;
import com.base.error.model.ErrorEnum;
import com.base.error.model.ServiceError;
import org.bson.types.ObjectId;

import java.util.Optional;

public class ValidationBuilder {

    private ValidationBuilder() {
    }

    public static boolean isHexStringEmpty(String id) {
        return !ObjectId.isValid(id);
    }

    public static boolean isEmpty(String name) {
        return null == name;
    }

    public static boolean isEmpty(Integer stock) {
        return null == stock;
    }

    public static boolean nameSizeExceeds(String name) {
        return name.length() > 100;
    }

    public static boolean isStockSizeValid(Integer stock) {
        return stock < 0;
    }

    public static Optional<ServiceError> buildError(ErrorMarker errorMarker, String errorMessage) {
        return Optional.of(
            ErrorFactory.fromErrorToken(
                    errorMarker.getErrorToken(BookError.TOP_ERROR_DOMAIN, errorMessage))
                .withErrorMarker(ErrorEnum.REQUEST_NOT_VERIFIED));
    }
}
