package com.base.controller.configuration;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class BaseServiceControllerTypeFilter extends AbstractClassTestingTypeFilter {

    private static final List<Pattern> controllerRelatedPackages = Arrays.asList(Pattern.compile("com.base.controller.*"), Pattern.compile("com.base.error.*"), Pattern.compile("com.base.converter.*"), Pattern.compile("com.base.application.*"));

    @Override
    protected boolean match(ClassMetadata metadata) {
        return controllerRelatedPackages.stream().anyMatch((pattern) -> {
            return pattern.matcher(metadata.getClassName()).matches();
        });
    }
}
