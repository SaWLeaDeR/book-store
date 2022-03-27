package com.base.error;

import com.base.exception.PropertyLoadingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

@Component
public class ExternalErrorCodeLocator {

    @Autowired
    @Qualifier("externalCallErrorMappingProperties")
    private Properties externalCallErrorMappingProperties;

    public String getMappedCode(String errorCode, String message, String... errorNamespaces) {
        String mappedCodeKey = getMappedPropertyName(errorCode, errorNamespaces);

        if (externalCallErrorMappingProperties.containsKey(mappedCodeKey)) {
            return externalCallErrorMappingProperties.getProperty(mappedCodeKey);
        } else {
            throw new PropertyLoadingException(format("There is no mapped error key for: %s, message: %s", mappedCodeKey, message));
        }
    }

    private String getMappedPropertyName(String errorCode, String... errorNamespaces) {
        return Stream.of(errorNamespaces).collect(Collectors.joining(".", "", "." + errorCode)).trim();
    }
}
