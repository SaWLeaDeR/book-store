package com.base.model.response;

import com.base.error.model.ServiceError;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class ServiceResponse<T> {

    private Optional<T> data;

    private Set<ServiceError> errors = new HashSet<>();

    private ServiceResponse(Optional<T> data) {
        this.data = data;
    }

    public static <T> ServiceResponse<T> empty() {
        return new ServiceResponse<>(Optional.empty());
    }

    public static <T> ServiceResponse<T> of(T data) {
        return new ServiceResponse<>(Optional.ofNullable(data));
    }

    public Optional<T> getData() {
        return data;
    }

    public Set<ServiceError> getErrors() {
        return errors;
    }

    public static <T> ServiceResponse<T> ofError(ServiceError serviceError) {
        ServiceResponse<T> serviceResponse = ServiceResponse.empty();
        serviceResponse.addError(serviceError);

        return serviceResponse;
    }

    private ServiceResponse<T> addError(ServiceError serviceError) {
        Objects.requireNonNull(serviceError);

        errors.add(serviceError);
        return this;
    }

    public Set<ServiceError> getServiceErrors() {
        return errors;
    }

    public void addServiceErrors(Set<ServiceError> serviceErrors) {
        getErrors().addAll(serviceErrors);
    }

    public ServiceError getFirstError() {
        return !CollectionUtils.isEmpty(getErrors()) ? (ServiceError) getErrors().stream().findFirst().orElse((ServiceError) null) : null;
    }

    public boolean isSuccessful() {
        return errors.isEmpty();
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public <R> ServiceResponse<R> map(Function<? super ServiceResponse<T>, ServiceResponse<R>> var1) {
        Objects.requireNonNull(var1);
        return var1.apply(this);
    }

    public <R> ServiceResponse<R> ifSuccessfullyPresent(Function<ServiceResponse<T>, ServiceResponse<R>> var1) {
        Objects.requireNonNull(var1);
        return this.filter(ServiceResponse::isSuccessful)
            .ifPresent(var1);
    }

    public ServiceResponse<T> filter(Predicate<ServiceResponse<T>> var1) {
        Objects.requireNonNull(var1);
        return data.isEmpty() ? this : (var1.test(this) ? this : getEmptyResultWithErrors());
    }

    private <R> ServiceResponse<R> getEmptyResultWithErrors() {
        ServiceResponse<R> errorResult = ServiceResponse.empty();
        errorResult.addServiceErrors(getServiceErrors());
        return errorResult;
    }

    public <R> R successOrError(Function<ServiceResponse<T>, R> success, Function<ServiceResponse<T>, R> error) {
        Objects.requireNonNull(success);
        Objects.requireNonNull(error);
        return isSuccessful() ? success.apply(this) : error.apply(this);
    }

    public <R> ServiceResponse<R> ifPresent(Function<ServiceResponse<T>, ServiceResponse<R>> var1) {
        Objects.requireNonNull(var1);
        return !data.isPresent() ? getEmptyResultWithErrors() : var1.apply(this);
    }
}
