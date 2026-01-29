package com.ian.springcore;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * - 기본 설정 정보 (AppConfig)는 프로젝트 루트에 두는 것이 관례
 * - 프로젝트 애플리케이션 (@SpringBootApplication) 역시 프로젝트 루트에 두는 것이 관례
 * - 컴포넌트 스캔처럼 애너테이션을 자동으로 인식해주는 기술은 자바가 아닌 스프링에서 제공하는 기능
 */
@Configuration
/**
 * @ComponentScan: @Component 애너테이션이 붙은 객체를 스프링 빈으로 자동 등록
 * 속성
 * - basePackageClasses: 특정 패키지에서 부터 스캔하도록 범위 지정 -> default = 현재 config의 패키지부터 하위 패키지까지 모두 스캔
 * - includeFilters: 컴포넌트 스캔에 포함할 대상
 * - excludeFilters: 컴포넌트 스캔에 제외할 대상
 * - @Filter(type = FilterType.ANNOTATION) -> type의 default 값이 ANNOTATION이기 때문에 생략해도 정상적으로 동작
 *      - ANNOTATION: 기본값, 애너테이션을 인식해서 동작
 *      - ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작
 *      - ASPECTJ: AspectJ 패턴 사용
 *      - REGEX: 정규 표현식
 *      - CUSTOM: TypeFilter라는 인터페이스를 구현해서 사용
 */
@ComponentScan(
        // 빈으로 등록하지 않을 객체 필터링 -> 이전에 작성한 config 제외
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = {Configuration.class}

        )
)
public class AutoAppConfig {
}
