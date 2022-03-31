package com.ing.sb.storeManagement.middleware;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class LoggingMiddleware implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(LoggingMiddleware.class);

    private static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-Id";
    private static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";

    long startTime = 0L;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String correlationId = getCorrelationIdFromHeader(request);
        MDC.put(CORRELATION_ID_LOG_VAR_NAME, correlationId);
        Thread.currentThread().setName(correlationId);

        logger.info("[ REQUEST STARTED ] {} {} {} ...", correlationId, request.getMethod(), request.getRequestURI());
        startTime = System.currentTimeMillis();

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(CORRELATION_ID_LOG_VAR_NAME);

        logger.info("[ REQUEST FINISHED ] {} {} {} duration {}ms ...",
                request.getMethod(), response.getStatus(), request.getRequestURI(),
                System.currentTimeMillis() - startTime);
        startTime = 0;
    }

    private String getCorrelationIdFromHeader(final HttpServletRequest request) {
        String correlationId = request.getHeader(CORRELATION_ID_HEADER_NAME);
        if (StringUtils.isBlank(correlationId)) {
            correlationId = generateUniqueCorrelationId();
        }
        return correlationId;
    }

    private String generateUniqueCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
