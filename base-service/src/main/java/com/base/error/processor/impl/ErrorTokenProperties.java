package com.base.error.processor.impl;


import com.base.error.configuration.ErrorMappingConfigurationProperty;
import com.base.exception.PropertyLoadingException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component("baseErrorTokenProperties")
public class ErrorTokenProperties extends PathMatchingResource {

    ErrorTokenProperties(
            @Qualifier(value = "internalErrorMappingConfiguration") ErrorMappingConfigurationProperty errorMappingConfigurationProperty) {
        super(errorMappingConfigurationProperty);
    }

    @Override
    protected void postLoadCheck(String topDomain, Properties properties) {
        properties.forEach((key, value) -> this.validateKey(topDomain, key));

        final Collection<Object> values = this.getCodes(properties);

        int numberOfErrorCodes = values.size();
        int uniqueErrorCodeSize = new HashSet<>(values).size();

        if (numberOfErrorCodes != uniqueErrorCodeSize) {
            throw new PropertyLoadingException(
                    String.format("Duplicate error codes found! on %s", topDomain)
            );
        }
    }

    private List<Object> getCodes(Properties properties) {
        return properties.entrySet().stream()
                .filter(entry -> ((String) entry.getKey()).endsWith(".code"))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    private void validateKey(String topDomain, Object keyObject) {
        String key = (String) keyObject;
        if (!key.startsWith(topDomain)) {
            throw new PropertyLoadingException(
                    String.format("All keys should be started with 'topDomain' name. Top domain:%s,\tInvalid key:%s",
                            topDomain, key)
            );
        }
    }

}
