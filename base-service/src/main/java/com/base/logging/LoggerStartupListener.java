package com.base.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;

public class LoggerStartupListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {
    private static final String LOG_FILE_NAME = "LOG_FILE_NAME";

    private static final String APPLICATION_VERSION = "application.version";

    private boolean started = false;

    @Override
    public void start() {
        if (started) return;

        String applicationVersion = getSystemProperty(APPLICATION_VERSION);

        Context context = getContext();
        context.putProperty(LOG_FILE_NAME, applicationVersion);

        started = true;
    }


    @Override
    public boolean isResetResistant() {
        return true;
    }

    @Override
    public void onStart(LoggerContext loggerContext) {
    }

    @Override
    public void onReset(LoggerContext loggerContext) {
    }

    @Override
    public void onStop(LoggerContext loggerContext) {
    }

    @Override
    public void onLevelChange(Logger logger, Level level) {
    }

    @Override
    public void stop() {
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    private String getSystemProperty(String propertyName) {
        return System.getProperty(propertyName);
    }
}
