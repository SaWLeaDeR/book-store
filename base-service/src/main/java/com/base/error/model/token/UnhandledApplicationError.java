package com.base.error.model.token;

import com.base.error.model.ErrorDomain;
import com.base.error.model.ServiceErrorDetail;

public class UnhandledApplicationError extends ServiceErrorDetail {

    private static final long serialVersionUID = -8435291065572074658L;

    private static final String UNHANDLED_APPLICATION_ERROR_CODE = "500";
    private static final String UNHANDLED_APPLICATION_ERROR_MESSAGE = "Unhandled application error";

    public UnhandledApplicationError(String... topDomain) {
        super(UNHANDLED_APPLICATION_ERROR_CODE, ErrorDomain.fromDomains(topDomain).getTopDomain(), UNHANDLED_APPLICATION_ERROR_MESSAGE);
    }
}
