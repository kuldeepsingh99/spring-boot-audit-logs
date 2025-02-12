package com.portal.audit.aspect;


import com.portal.audit.service.EventLogService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;

@Aspect
@Component
public class LoggingAspect {

    private final EventLogService eventLogService;
    private final HttpServletRequest request;

    public LoggingAspect(EventLogService eventLogService, HttpServletRequest request) {
        this.eventLogService = eventLogService;
        this.request = request;
    }

    @Around("@annotation(LogEvent)")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogEvent logEvent = method.getAnnotation(LogEvent.class);

        String serviceName = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = signature.getName();
        String action = logEvent.action();
        String performedBy = request.getHeader("X-User");

        if (performedBy == null || performedBy.isEmpty()) {
            performedBy = "unknown_user";
        }

        Object result = joinPoint.proceed();

        eventLogService.logEvent(serviceName, methodName, action, performedBy);

        return result;
    }
}
