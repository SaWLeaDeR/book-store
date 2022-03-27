package com.base.error.configuration;

import com.base.error.processor.BaseErrorMappingProcessor;
import com.base.exception.PropertyLoadingException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class ErrorPropertiesConfiguration {

    @Bean("externalCallErrorMappingProperties")
    public Properties errorMappingProperties(
            @Qualifier("errorTokenProperties") Properties errorTokenProperties,
            @Qualifier("externalCallErrorProperties") BaseErrorMappingProcessor externalCallErrorProperties) {
        Properties errorMappingProperties = externalCallErrorProperties.getProperties();

        this.validateValidateExternalErrors(errorTokenProperties, errorMappingProperties);

        return errorMappingProperties;
    }

    @Bean("errorTokenProperties")
    public Properties errorTokenProperties(@Qualifier("baseErrorTokenProperties")
                                                   BaseErrorMappingProcessor baseErrorMappingProcessor) {
        return baseErrorMappingProcessor.getProperties();
    }

    private void validateValidateExternalErrors(Properties errorTokenProperties, Properties errorProperties) {
        errorProperties.forEach(
                (key, value) -> validateExternalError(errorTokenProperties, key, value)
        );
    }

    private void validateExternalError(Properties errorTokenProperties, Object key, Object value) {
        String errorTokenCode = value + ".code";
        if (!errorTokenProperties.containsKey(errorTokenCode)) {
            throw new PropertyLoadingException(
                    String.format("Invalid external mapping value! Error token does not exists! External mapping key:%s", key)
            );
        }
    }

}
