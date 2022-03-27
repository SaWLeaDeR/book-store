package com.base.application.profile;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

public class ApplicationEnvironmentHandler implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final String PROFILE = "spring.profiles.active";

    private static final String CONFIG_SERVER = "spring.cloud.config.uri";

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();
        checkActiveProfiles(activeProfiles);
        checkConfigProperties(environment);
    }

    private void checkConfigProperties(ConfigurableEnvironment environment) {
        String configServerProperty = environment.getProperty(CONFIG_SERVER);

        if (StringUtils.isNotEmpty(configServerProperty)) {
            throw new RuntimeException(String.format("Invalid configuration. Invalid %s parameter found", CONFIG_SERVER));
        }
    }

    private void checkActiveProfiles(String[] activeProfiles) {
        boolean hasEmptyProfile = Arrays.stream(activeProfiles)
                .anyMatch(
                        profile -> StringUtils.isEmpty(profile) || profile.equals(CustomProfile.DEFAULT));

        if (hasEmptyProfile || activeProfiles.length == 0) {
            throw new RuntimeException(String.format("Invalid configuration. No valid %s parameter found", PROFILE));
        }
    }
}
