package com.base.bookservice.validation.impl;

import com.base.bookservice.model.error.BookError;
import com.base.bookservice.model.request.BookCreateRequest;
import com.base.bookservice.model.request.BookGetRequest;
import com.base.bookservice.validation.BookRequestValidationService;
import com.base.bookservice.validation.model.dto.BookParameterValidatorInput;
import com.base.bookservice.validation.model.dto.BookValidatorInput;
import com.base.bookservice.validation.model.enumeration.BookRequestType;
import com.base.bookservice.validation.parameter.BookParameterValidatorService;
import com.base.error.ErrorFactory;
import com.base.error.ErrorMarker;
import com.base.error.model.ErrorEnum;
import com.base.error.model.ServiceError;
import com.base.model.request.GenericRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookGetRequestValidationServiceImpl implements BookRequestValidationService {

    private final List<BookParameterValidatorService> bookParameterValidatorServices;
    private final ErrorMarker errorMarker;

    public BookGetRequestValidationServiceImpl(
        List<BookParameterValidatorService> bookParameterValidatorServices,
        ErrorMarker errorMarker) {
        this.bookParameterValidatorServices = bookParameterValidatorServices;
        this.errorMarker = errorMarker;
    }

    @Override
    public <T extends GenericRequest> Optional<ServiceError> validate(
        BookValidatorInput<T> validatorInput) {
        final BookGetRequest request = (BookGetRequest) validatorInput.getRequest();

        if (null == request) {
            return Optional.of(ErrorFactory.fromErrorToken(
                    errorMarker.getErrorToken(BookError.TOP_ERROR_DOMAIN, BookError.EMPTY_REQUEST))
                .withErrorMarker(ErrorEnum.REQUEST_NOT_VERIFIED));
        }

        final BookParameterValidatorInput parameterValidatorInput = BookParameterValidatorInput.builder()
            .name(request.getName())
            .seller(request.getSeller())
            .build();

        return bookParameterValidatorServices.stream()
            .map(validator -> validator.validate(parameterValidatorInput, true))
            .filter(Optional::isPresent)
            .findFirst()
            .orElseGet(Optional::empty);
    }

    @Override
    public BookRequestType getValidatorType() {
        return BookRequestType.GET;
    }
}
