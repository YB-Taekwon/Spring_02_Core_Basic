package com.ian.springcore;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // @Component 애너테이션이 붙은 객체를 스프링 빈으로 자동 등록
        // 빈으로 등록하지 않을 객체 필터링 -> 이전에 작성한 config 제외
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Configuration.class})
)
public class AutoAppConfig {
}
