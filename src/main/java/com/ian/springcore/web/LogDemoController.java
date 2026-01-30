package com.ian.springcore.web;

import com.ian.springcore.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/log-demo")
public class LogDemoController {

    private final LogDemoService logDemoService;
    //    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;

    /**
     * Provider 사용 전
     * 싱글톤 컨테이너가 빈을 생성할 때 요청 정보가 없어서 MyLogger 빈(scope=request)을 생성할 수 없어서 예외 발생
     * Provider 사용 후
     * 빈 조회 시점을 실제 요청 처리 시점으로 늦춰서 빈이 정상적으로 생성 및 초기화가 됨
     */
    @GetMapping
    public String logDemo(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();

//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestUrl(requestUrl);
        myLogger.log("Controller Test");

        logDemoService.logic("testId");

        return "OK";
    }
}
