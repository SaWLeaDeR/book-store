package com.base.error;

import com.base.error.model.ErrorEnum;
import com.base.error.model.ServiceError;
import com.base.error.model.ServiceErrorDetail;
import com.base.error.model.dto.ErrorLogLevel;

public final class ErrorFactory {

    private ErrorFactory() {
    }

    public static ErrorBuilder.ErrorMarkerBuilder fromErrorToken(ServiceErrorDetail errorToken) {
        return ErrorBuilder.create().fromErrorToken(errorToken);
    }

    public ServiceError create(ServiceErrorDetail errorDetail, ErrorEnum errorEnum, ErrorLogLevel errorLogLevel) {
        return ErrorFactory.fromErrorToken(errorDetail).withErrorMarker(errorEnum).atErrorLogLevel(errorLogLevel);
    }

    public ServiceError create(ServiceErrorDetail errorDetail, ErrorEnum errorEnum) {
        return ErrorFactory.fromErrorToken(errorDetail).withErrorMarker(errorEnum);
    }

}
