package com.base.controller.locator;

import com.base.error.model.ErrorEnum;
import com.base.error.model.ServiceError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.EnumMap;

import static com.base.error.model.ErrorEnum.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.OK;

@Component
public class ErrorEnumHttpStatusLocator {

    private final EnumMap<ErrorEnum, HttpStatus> errorMarkerHttpStatusMap = new EnumMap<>(ErrorEnum.class);

    public ErrorEnumHttpStatusLocator() {
        this.errorMarkerHttpStatusMap.put(MALFORMED_REQUEST, BAD_REQUEST);
        this.errorMarkerHttpStatusMap.put(UNHANDLED_APPLICATION_ERROR, INTERNAL_SERVER_ERROR);
        this.errorMarkerHttpStatusMap.put(REQUEST_NOT_VERIFIED, BAD_REQUEST);
        this.errorMarkerHttpStatusMap.put(RESOURCE_NOT_FOUND, NOT_FOUND);
        this.errorMarkerHttpStatusMap.put(REQUEST_CANNOT_BE_COMPLETED, CONFLICT);
    }

    public HttpStatus locateHttpStatus(ServiceError serviceError) {
        HttpStatus status = errorMarkerHttpStatusMap.get(serviceError.getErrorType());
        if (status == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return status;
    }
}
