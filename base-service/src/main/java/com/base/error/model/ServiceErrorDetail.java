package com.base.error.model;

import java.io.Serializable;
import java.util.Objects;

public class ServiceErrorDetail implements Serializable {
    private static final long serialVersionUID = 7489613246350116962L;

    private final String code;
    private final String topDomain;
    private String message;

    public ServiceErrorDetail(String code, String topDomain, String message) {
        this.code = code;
        this.topDomain = topDomain;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopDomain() {
        return topDomain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceErrorDetail that = (ServiceErrorDetail) o;
        return Objects.equals(code, that.code) && Objects.equals(topDomain, that.topDomain) && Objects.equals(message,
                that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, topDomain, message);
    }

    @Override
    public String toString() {
        return "ErrorDetail{" +
                "code='" + code + '\'' +
                ", topDomain='" + topDomain + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
