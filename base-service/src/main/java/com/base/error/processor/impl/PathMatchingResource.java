package com.base.error.processor.impl;

import com.base.error.configuration.ErrorMappingConfigurationProperty;
import com.base.error.processor.BaseErrorMappingProcessor;
import com.base.exception.PropertyLoadingException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.Properties;

public abstract class PathMatchingResource implements BaseErrorMappingProcessor {

    private final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    private static final String DOT = ".";
    private static final int ZERO_VALUE = 0;

    private final ErrorMappingConfigurationProperty errorMappingConfigurationProperty;

    PathMatchingResource(
            ErrorMappingConfigurationProperty errorMappingConfigurationProperty) {
        this.errorMappingConfigurationProperty = errorMappingConfigurationProperty;
    }

    protected abstract void postLoadCheck(String filename, Properties properties);

    @Override
    public Properties getProperties() {
        Resource[] propertiesLocations = getPropertiesResources();
        try {
            Properties mergedProperties = new Properties();
            for (Resource propertiesLocation : propertiesLocations) {
                Properties properties = new Properties();
                properties.load(new EncodedResource(propertiesLocation, (String) null).getReader());

                postLoadCheck(getFileNameWithoutExtension(propertiesLocation.getFilename()), properties);

                mergedProperties.putAll(properties);
            }

            return mergedProperties;
        } catch (IOException e) {
            throw new PropertyLoadingException("Error properties configuration error", e);
        }
    }

    private Resource[] getPropertiesResources() {
        try {
            return resolver.getResources(errorMappingConfigurationProperty.getResource());
        } catch (IOException e) {
            throw new PropertyLoadingException("Error properties configuration error", e);
        }
    }

    private String getFileNameWithoutExtension(String filename) {
        int seperator = filename.lastIndexOf(DOT);
        return filename.substring(ZERO_VALUE, seperator);
    }

}
