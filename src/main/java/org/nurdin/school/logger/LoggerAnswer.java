package org.nurdin.school.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggerAnswer {

    @Pointcut("execution(* org.nurdin.school..*(..)) && !within(org.nurdin.school.security..*)")
    private void publicMethodsFromLoggingPackage() {
    }

    @Before(value = "publicMethodsFromLoggingPackage()")
    public void logBefore(JoinPoint joinPoint) {  // Тут просто JoinPoint, без return
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("---------------------->[BEFORE] {}.{} вызван с параметрами: {}", className, methodName, Arrays.toString(args));
    }
    @AfterReturning(value = "publicMethodsFromLoggingPackage()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        log.info("---------------------->[AFTER] {}.{} завершил выполнение, результат: {}", className, methodName, formatResult(result));
    }

    @AfterThrowing(pointcut = "publicMethodsFromLoggingPackage()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        StackTraceElement firstElement = ex.getStackTrace().length > 0 ? ex.getStackTrace()[0] : null;

        log.error("-------------------->[ERROR] Ошибка в {}.{}: {} ({}:{})",
            className, methodName, ex.getMessage(),
            firstElement != null ? firstElement.getFileName() : "Unknown",
            firstElement != null ? firstElement.getLineNumber() : -1);
    }

    private String formatResult(Object result) {
        if (result instanceof Iterable<?>) {
            StringBuilder sb = new StringBuilder("[");
            for (Object obj : (Iterable<?>) result) {
                sb.append(obj.toString()).append(", ");
            }
            if (sb.length() > 1) sb.setLength(sb.length() - 2);
            sb.append("]");
            return sb.toString();
        }
        return String.valueOf(result);
    }
}

