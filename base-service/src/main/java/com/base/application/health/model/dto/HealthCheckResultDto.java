package com.base.application.health.model.dto;

import com.base.model.dto.GenericDto;

public class HealthCheckResultDto extends GenericDto {

    private static final long serialVersionUID = 4014245854331392123L;

    private boolean status;
    private String message;

    public HealthCheckResultDto() {
        this(true);
    }

    public HealthCheckResultDto(Boolean status) {
        this.status = status;
        this.message = Boolean.TRUE.equals(this.status) ? "OK" : "FAIL";
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
