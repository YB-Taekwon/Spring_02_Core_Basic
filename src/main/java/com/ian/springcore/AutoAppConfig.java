package com.ian.springcore;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * - 기본 설정 정보 (AppConfig)는 프로젝트 루트에 두는 것이 관례
 * - 프로젝트 애플리케이션 (@SpringBootApplication) 역시 프로젝트 루트에 두는 것이 관례
 * - 컴포넌트 스캔처럼 애너테이션을 자동으로 인식해주는 기술은 자바가 아닌 스프링에서 제공하는 기능
 */
@Configuration
@ComponentScan( // @Component 애너테이션이 붙은 객체를 스프링 빈으로 자동 등록
        // basePackageClasses: 특정 패키지에서 부터 스캔하도록 범위 지정 -> default = 현재 config의 패키지부터 하위 패키지까지 모두 스캔
        // 빈으로 등록하지 않을 객체 필터링 -> 이전에 작성한 config 제외
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Configuration.class}

        )
)
public class AutoAppConfig {
}
