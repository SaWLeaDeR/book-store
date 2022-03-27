package com.base.error.processor.impl;


import com.base.error.configuration.ErrorMappingConfigurationProperty;
import com.base.exception.PropertyLoadingException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.regex.Pattern;

@Component("externalCallErrorProperties")
public class ExternalErrorProperties extends PathMatchingResource {

    private static final Pattern EXTERNAL_MAPPING_FILE_NAME_FORMAT = Pattern.compile("^[a-z\\-]+$");

    ExternalErrorProperties(
            @Qualifier(value = "externalErrorMappingConfiguration") ErrorMappingConfigurationProperty errorMappingConfigurationProperty) {
        super(errorMappingConfigurationProperty);
    }

    @Override
    protected void postLoadCheck(String filename, Properties properties) {
        this.validateExternalMappingFileName(filename);
    }

    private void validateExternalMappingFileName(String filename) {
        if (!EXTERNAL_MAPPING_FILE_NAME_FORMAT.matcher(filename).matches()) {
            throw new PropertyLoadingException(
                    String.format("Invalid file name for external-mappings!. File name:%s", filename)
            );
        }
    }

}
