package org.example.expert.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
public class AdminAspect {

    @Pointcut("@annotation(org.example.expert.annotation.AdminLog)")
    private void adminLogAnnotation() {
    }

    /**
     * 어드바이스 : 어노테이션 범위 기반
     */
    @Around("adminLogAnnotation()")
    public Object adviceAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        // 요청 시간 시간 기록
        long logTime = System.currentTimeMillis();
        log.info("::: 요청 시간 시간 기록 : {}ms", logTime);

        // HttpServletRequest 객체를 가져오기 위한 코드
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        // 요청한 사용자의 ID
        Long userId = (Long) req.getAttribute("userId");
        log.info("::: 요청한 사용자의 ID : {}", userId);


        // API 요청 URL
        String requestURL = req.getRequestURI();
        log.info("::: API 요청 URL : {}", requestURL);

        Object result = joinPoint.proceed();
        return result;

    }

}


