package com.base.controller.advice;

import com.base.controller.GenericController;
import com.base.model.response.EndpointResponse;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.concurrent.TimeUnit;

import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;


@ControllerAdvice(assignableTypes = GenericController.class)
@Order(LOWEST_PRECEDENCE)
public class ElapsedTimeSetter implements ResponseBodyAdvice<EndpointResponse> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public EndpointResponse beforeBodyWrite(EndpointResponse endpointResponse, MethodParameter methodParameter, MediaType mediaType,
                                            Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                            ServerHttpResponse serverHttpResponse) {
        ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) serverHttpRequest;
        long startTimeInMillis = (long) servletServerHttpRequest.getServletRequest().getAttribute("startTimeInMillis");
        long currentTimeInMillis = System.currentTimeMillis();
        endpointResponse.setElapsedTime(Long.toString(TimeUnit.MILLISECONDS.toMillis(currentTimeInMillis - startTimeInMillis)));
        return endpointResponse;
    }
}
