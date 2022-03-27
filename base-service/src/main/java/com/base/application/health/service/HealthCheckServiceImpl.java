package com.base.application.health.service;

import com.base.application.health.model.dto.HealthCheckResultDto;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckServiceImpl implements HealthCheckService {

    @Override
    public HealthCheckResultDto healthCheck() {
        return new HealthCheckResultDto();
    }
}
