package com.base.error.model.dto;

import com.base.error.model.GenericErrorDto;

public class ExceptionDto extends GenericErrorDto {
    private static final long serialVersionUID = 3070984076956583363L;

    private String message;
    private String rootCause;
    private String applicationLogStackTrace;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public String getApplicationLogStackTrace() {
        return applicationLogStackTrace;
    }

    public void setApplicationLogStackTrace(String applicationLogStackTrace) {
        this.applicationLogStackTrace = applicationLogStackTrace;
    }
}
