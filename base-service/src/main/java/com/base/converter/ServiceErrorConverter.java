package com.base.converter;

import com.base.error.model.dto.ErrorDto;

import java.util.Collection;

public interface ServiceErrorConverter<T extends ErrorDto, R> {

    R convert(T error, String... errorNamespaces);

    Collection<R> convert(Collection<T> serviceError, String... errorNamespaces);
}
