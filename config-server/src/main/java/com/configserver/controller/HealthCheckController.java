package com.configserver.controller;

import com.configserver.model.response.HealthCheckResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class HealthCheckController {

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public HealthCheckResponse getStatus() {
        return new HealthCheckResponse(true, "OK");
    }
}
