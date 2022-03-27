package com.base.error.configuration.resource;

import com.base.error.configuration.ErrorMappingConfigurationProperty;
import org.springframework.context.annotation.Configuration;


@Configuration("internalErrorMappingConfiguration")
public class InternalErrorMappingConfiguration implements ErrorMappingConfigurationProperty {

    private static final String DEFAULT_EXTERNAL_ERROR_MAPPING_RESOURCES_PATH_PATTERN = "classpath*:com/*/i18n/internal/*.properties";

    @Override
    public String getResource() {
        return DEFAULT_EXTERNAL_ERROR_MAPPING_RESOURCES_PATH_PATTERN;
    }
}