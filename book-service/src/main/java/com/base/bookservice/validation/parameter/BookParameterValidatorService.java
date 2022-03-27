package com.base.bookservice.validation.parameter;

import com.base.bookservice.validation.model.dto.BookParameterValidatorInput;
import com.base.error.model.ServiceError;

import java.util.Optional;

public interface BookParameterValidatorService {

    Optional<ServiceError> validate(BookParameterValidatorInput parameterValidatorInput, boolean isEmptyValid);
}
