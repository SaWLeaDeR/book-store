package com.base.controller.builder;

import com.base.controller.locator.ErrorEnumHttpStatusLocator;
import com.base.error.model.ServiceError;
import com.base.model.dto.GenericDto;
import com.base.model.response.EndpointResponse;
import com.base.model.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;

import static org.springframework.http.HttpStatus.OK;

@Component
public class HttpStatusServiceResponseBuilder<B extends GenericDto, R extends ServiceResponse<B>, C extends EndpointResponse<B>>
    implements EndpointResponseBuilder<B, R, C> {

    @Autowired
    private ErrorEnumHttpStatusLocator endpointHttpStatusLocator;

    private static final HttpStatus defaultOkStatus = OK;

    @Override
    public void build(R optionalMethodResult, C endpointResponse) {
        endpointResponse.setHttpStatus(resolveHttpStatus(optionalMethodResult));
    }

    private HttpStatus resolveHttpStatus(R serviceMethodResponse) {
        Set<ServiceError> serviceErrors = serviceMethodResponse.getServiceErrors();
        if (CollectionUtils.isEmpty(serviceErrors)) {
            return defaultOkStatus;
        } else {
            return endpointHttpStatusLocator.locateHttpStatus(serviceErrors.iterator().next());
        }
    }

}
