package com.configserver.model.response;

import java.io.Serializable;
import java.util.Objects;

public class HealthCheckResponse implements Serializable {

    private static final long serialVersionUID = 4965652981860570191L;

    private boolean status;
    private String message;

    public HealthCheckResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthCheckResponse that = (HealthCheckResponse) o;
        return status == that.status && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message);
    }

    @Override
    public String toString() {
        return "HealthCheckResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
