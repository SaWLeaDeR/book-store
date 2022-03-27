package com.base.bookservice.validation.service;

import com.base.bookservice.validation.model.enumeration.BookRequestType;
import com.base.model.request.GenericRequest;
import com.base.model.response.ServiceResponse;

public interface BookValidationService {

    <T extends GenericRequest> ServiceResponse<T> validateOperation(BookRequestType type, T request);
}
