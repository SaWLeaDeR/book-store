package com.base.bookservice.validation.service.impl;

import com.base.bookservice.model.error.BookError;
import com.base.bookservice.validation.BookRequestValidationService;
import com.base.bookservice.validation.model.dto.BookValidatorInput;
import com.base.bookservice.validation.model.enumeration.BookRequestType;
import com.base.bookservice.validation.service.BookValidationService;
import com.base.error.ErrorFactory;
import com.base.error.ErrorMarker;
import com.base.error.model.ErrorEnum;
import com.base.error.model.ServiceError;
import com.base.model.request.GenericRequest;
import com.base.model.response.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class BookValidationServiceImpl implements BookValidationService {

    private final Map<BookRequestType, BookRequestValidationService> bookRequestValidationServiceMap;
    private final ErrorMarker errorMarker;

    public BookValidationServiceImpl(
        Map<BookRequestType, BookRequestValidationService> bookRequestValidationServiceMap, ErrorMarker errorMarker) {
        this.bookRequestValidationServiceMap = bookRequestValidationServiceMap;
        this.errorMarker = errorMarker;
    }

    @Override
    public <T extends GenericRequest> ServiceResponse<T> validateOperation(
        BookRequestType type, T request) {
        final BookRequestValidationService bookRequestValidator = bookRequestValidationServiceMap.get(type);

        if (bookRequestValidator == null) {
            return ServiceResponse.ofError(
                getError(BookError.VALIDATOR_NOT_FOUND));
        }

        final Optional<ServiceError> validationResult = bookRequestValidator
            .validate(new BookValidatorInput<>(request));

        return validationResult.<ServiceResponse<T>>map(ServiceResponse::ofError).orElseGet(() -> ServiceResponse.of(request));
    }

    private ServiceError getError(String errorMessage) {
        return ErrorFactory.fromErrorToken(
                errorMarker.getErrorToken(BookError.TOP_ERROR_DOMAIN, errorMessage))
            .withErrorMarker(ErrorEnum.REQUEST_NOT_VERIFIED);
    }
}
