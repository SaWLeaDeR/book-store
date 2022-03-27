package com.base.bookservice.validation.impl;

import com.base.bookservice.model.error.BookError;
import com.base.bookservice.model.request.BookCreateRequest;
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
public class BookCreateRequestValidationServiceImpl implements BookRequestValidationService {

    private final List<BookParameterValidatorService> bookParameterValidators;
    private final ErrorMarker errorMarker;

    public BookCreateRequestValidationServiceImpl(
        List<BookParameterValidatorService> bookParameterValidators,
        ErrorMarker errorMarker) {
        this.bookParameterValidators = bookParameterValidators;
        this.errorMarker = errorMarker;
    }

    @Override
    public <T extends GenericRequest> Optional<ServiceError> validate(
        BookValidatorInput<T> validatorInput) {
        final BookCreateRequest request = (BookCreateRequest) validatorInput.getRequest();

        if (null == request) {
            return Optional.of(ErrorFactory.fromErrorToken(
                    errorMarker.getErrorToken(BookError.TOP_ERROR_DOMAIN, BookError.EMPTY_REQUEST))
                .withErrorMarker(ErrorEnum.REQUEST_NOT_VERIFIED));
        }

        final BookParameterValidatorInput parameterValidatorInput = BookParameterValidatorInput.builder()
            .name(request.getName())
            .seller(request.getSeller())
            .stock(request.getStockCount())
            .build();

        return bookParameterValidators.stream()
            .map(validator -> validator.validate(parameterValidatorInput, false))
            .filter(Optional::isPresent)
            .findFirst()
            .orElseGet(Optional::empty);
    }

    @Override
    public BookRequestType getValidatorType() {
        return BookRequestType.CREATE;
    }
}
