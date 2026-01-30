package com.ian.springcore.web;

import com.ian.springcore.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    public void logic(String id) {
        myLogger.log("Service Id = " + id);
    }
}
