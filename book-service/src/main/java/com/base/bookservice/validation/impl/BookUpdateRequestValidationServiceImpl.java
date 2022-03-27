package com.base.bookservice.validation.impl;

import com.base.bookservice.model.dto.BookDto;
import com.base.bookservice.model.error.BookError;
import com.base.bookservice.model.request.delegate.BookUpdateDelegateRequest;
import com.base.bookservice.service.delegate.BookDelegateService;
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
import com.base.model.response.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookUpdateRequestValidationServiceImpl implements BookRequestValidationService {

    private final List<BookParameterValidatorService> bookParameterValidatorServices;
    private final BookDelegateService bookDelegateService;
    private final ErrorMarker errorMarker;


    public BookUpdateRequestValidationServiceImpl(
        List<BookParameterValidatorService> bookParameterValidatorServices,
        BookDelegateService bookDelegateService, ErrorMarker errorMarker) {
        this.bookParameterValidatorServices = bookParameterValidatorServices;
        this.bookDelegateService = bookDelegateService;
        this.errorMarker = errorMarker;
    }

    @Override
    public <T extends GenericRequest> Optional<ServiceError> validate(
        BookValidatorInput<T> validatorInput) {
        final BookUpdateDelegateRequest request = (BookUpdateDelegateRequest) validatorInput.getRequest();

        if (null == request) {
            return Optional.of(ErrorFactory.fromErrorToken(
                    errorMarker.getErrorToken(BookError.TOP_ERROR_DOMAIN, BookError.EMPTY_REQUEST))
                .withErrorMarker(ErrorEnum.REQUEST_NOT_VERIFIED));
        }

        final BookParameterValidatorInput parameterValidatorInput = BookParameterValidatorInput.builder()
            .id(request.getId())
            .name(request.getName())
            .seller(request.getSeller())
            .stock(request.getStock())
            .build();

        final Optional<ServiceError> serviceError = bookParameterValidatorServices.stream()
            .map(validator -> validator.validate(parameterValidatorInput, false))
            .filter(Optional::isPresent)
            .findFirst()
            .orElseGet(Optional::empty);

        if (serviceError.isPresent()) {
            return serviceError;
        }

        final ServiceResponse<BookDto> bookResponse = bookDelegateService.getBook(request.getId());

        if (!bookResponse.isSuccessful() || bookResponse.getData().isEmpty()) {
            return Optional.of(ErrorFactory.fromErrorToken(
                    errorMarker.getErrorToken(BookError.TOP_ERROR_DOMAIN, BookError.BOOK_NOT_FOUND))
                .withErrorMarker(ErrorEnum.RESOURCE_NOT_FOUND));
        }
        return Optional.empty();
    }

    @Override
    public BookRequestType getValidatorType() {
        return BookRequestType.UPDATE;
    }
}
