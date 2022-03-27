package com.base.converter.impl;

import com.base.converter.ServiceErrorConverter;
import com.base.error.ErrorFactory;
import com.base.error.ErrorMarker;
import com.base.error.ExternalErrorCodeLocator;
import com.base.error.model.ErrorEnum;
import com.base.error.model.ServiceError;
import com.base.error.model.ServiceErrorDetail;
import com.base.error.model.dto.ErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ServiceErrorConverterImpl implements ServiceErrorConverter<ErrorDto, ServiceError> {

    @Autowired
    private ErrorMarker errorMarker;

    @Autowired
    private ExternalErrorCodeLocator externalErrorCodeLocator;

    @Override
    public ServiceError convert(ErrorDto error, String... errorNamespaces) {
        final ServiceErrorDetail errorDetail = error.getErrorDetail();

        String errorCode = convertToDetail(errorDetail);
        final String errorKey = externalErrorCodeLocator.getMappedCode(errorCode, errorDetail.getMessage(), errorNamespaces);

        return ErrorFactory
            .fromErrorToken(errorMarker.getErrorToken(errorKey))
            .withErrorMarker(ErrorEnum.MALFORMED_REQUEST);
    }

    @Override
    public Collection<ServiceError> convert(Collection<ErrorDto> serviceError, String... errorNamespaces) {
        return serviceError
            .stream()
            .map(errorDto -> convert(errorDto, errorNamespaces))
            .collect(Collectors.toList());
    }

    private String convertToDetail(ServiceErrorDetail errorDetail) {
        return Stream.of(errorDetail.getTopDomain(), errorDetail.getCode())
            .filter(Objects::nonNull)
            .collect(Collectors.joining("."));
    }
}
