package com.ian.springcore.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 프록시를 사용하면 Provider를 사용하지 않아도 동일한 동작을 실행
 * - 프록시 객체는 실제 요청이 들어오면 그 때 실제 빈을 요청하는 위임 로직이 들어있음 (CGLIB에서 복제한 클래스를 주입하고 실제 요청 시 진짜 클래스를 호출)
 */
@Component
// request scope: 실제 요청이 들어올 때까지 조회 시점을 늦추는 것이 중요함
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // TARGET_CLASS: 프록시(가짜) 클래스를 생성해서 주입
public class MyLogger {

    private String uuid;
    private String requestUrl;

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "] [" + requestUrl + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
