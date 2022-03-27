package com.base.aspect;

import com.base.aspect.annotation.InternalLogger;
import com.base.aspect.model.Type;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Aspect
public class LoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);
    private final ExecutorService executorService;

    private LoggerAspect() {
        executorService = Executors.newSingleThreadExecutor();
    }

    @Pointcut("execution(public * com.base..*(..)) && @annotation(logger)")
    void loggerMethods(InternalLogger logger) {
    }

    @Before("loggerMethods(logger)")
    public void beforeLoggerMethod(JoinPoint joinPoint, InternalLogger logger) {
        CompletableFuture.runAsync(() -> {
            try {
                LOGGER.info(getMethodStartMessage(joinPoint, Type.BEFORE, logger.name()));
            } catch (Exception e) {
                LOGGER.error("Logfor=Before method invoice failed from{}, Calls={}", logger.name(), joinPoint.getSignature(), e);
            }
        }, executorService);
    }

    @AfterReturning(
        pointcut = "loggerMethods(logger)",
        returning = "returnVal")
    public void sendEventV2AfterReturning(JoinPoint joinPoint, InternalLogger logger, Object returnVal) {
        CompletableFuture.runAsync(() -> {
            try {
                LOGGER.info("Finished method invoice success request={} from={}, returns={}",
                    getMethodStartMessage(joinPoint, Type.AFTER, logger.name()),
                    logger.name(),
                    returnVal);
            } catch (Exception e) {
                LOGGER.error(
                    "Finished method invoice failed from{}, Returning={}", logger.name(), returnVal, e);
            }
        }, executorService);
    }

    private static String getMethodStartMessage(JoinPoint joinPoint, Type type, String name) {
        StringBuilder buffer = new StringBuilder();
        String[] parameterNames = ((MethodSignature) joinPoint.getStaticPart().getSignature()).getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();

        buffer.append("Method invoice ")
            .append(name)
            .append(" ")
            .append(type.getValue())
            .append(joinPoint.getSignature().toString());

        if (parameterNames.length > 0) {
            buffer.append(" Input ==>  (");

            for (int i = 0; i < parameterNames.length; i++) {
                buffer.append(parameterNames[i]);
                buffer.append("=");
                buffer.append(parameterValues[i] != null ? findParameterValue(parameterValues[i]) : "null");
                buffer.append(",");
            }

            buffer.deleteCharAt(buffer.length() - 1);
            buffer.append(")");
        }
        return buffer.toString();
    }

    private static Object findParameterValue(Object parameterValue) {
        if (parameterValue instanceof Object[]) {
            return Arrays.toString((Object[]) parameterValue);
        }
        return parameterValue;
    }
}
