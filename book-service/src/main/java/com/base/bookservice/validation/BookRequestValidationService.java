package com.base.bookservice.validation;

import com.base.bookservice.validation.model.dto.BookValidatorInput;
import com.base.bookservice.validation.model.enumeration.BookRequestType;
import com.base.error.model.ServiceError;
import com.base.model.request.GenericRequest;

import java.util.Optional;

public interface BookRequestValidationService {
    <T extends GenericRequest> Optional<ServiceError> validate(BookValidatorInput<T> validatorInput);

    BookRequestType getValidatorType();
}
