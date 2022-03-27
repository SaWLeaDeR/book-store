package com.base.controller.advice;

import com.base.controller.GenericController;
import com.base.converter.GenericConverter;
import com.base.model.response.EndpointResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;

@ControllerAdvice(assignableTypes = GenericController.class)
@Order(LOWEST_PRECEDENCE)
public class ExceptionAdvisor {

    @Autowired
    @Qualifier("exceptionEndpointResponseConverter")
    private GenericConverter<Throwable, EndpointResponse> exceptionEndpointResponseConverter;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public EndpointResponse handle(Exception exception) {
        return exceptionEndpointResponseConverter.convert(exception);
    }
}
