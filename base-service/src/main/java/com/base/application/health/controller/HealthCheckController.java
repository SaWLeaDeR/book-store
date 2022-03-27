package com.base.application.health.controller;

import com.base.application.health.model.dto.HealthCheckResultDto;
import com.base.application.health.service.HealthCheckService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    private final HealthCheckService healthCheckService;

    public HealthCheckController(HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public HealthCheckResultDto getStatus() {
        return healthCheckService.healthCheck();
    }
}
