package com.base.error;

import com.base.error.model.ErrorEnum;
import com.base.error.model.ServiceError;
import com.base.error.model.ServiceErrorDetail;

public class ErrorBuilder {

    public static ErrorTokenBuilder create() {
        return errorToken -> errorMarker -> new ServiceError(errorToken, errorMarker);
    }

    public interface ErrorTokenBuilder {
        ErrorMarkerBuilder fromErrorToken(ServiceErrorDetail errorToken);
    }

    public interface ErrorMarkerBuilder {
        ServiceError withErrorMarker(ErrorEnum errorEnum);
    }

}
