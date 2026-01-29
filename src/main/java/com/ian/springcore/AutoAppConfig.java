package com.ian.springcore;

import com.ian.springcore.member.MemberRepository;
import com.ian.springcore.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
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

        /**
         * 상황
         * MemoryMemberRepository 클래스에 @Repository 애너테이션이 있기 때문에 스프링에서 memoryMemberRepository라는 이름의 빈을 자동으로 등록
         * AutoAppConfig에 memoryMemberRepository라는 이름의 빈을 수동으로 등록
         * 동일한 이름의 빈이 중복되면 원래는 예외 발생
         * 결과
         * 하나는 Configuration에 수동 등록을 했고, 하나는 스프링에서 자동으로 등록하는 경우 수동으로 작성한 빈의 등록이 우선권을 갖음
         * 즉 스프링에서는 수동 등록한 빈이 오버라이딩 되어 하나의 빈만 등록되고 예외가 발생하지 않음
         * 단, 스프링 부트에서는 자동 오버라이딩 false를 기본값으로 지정하여 예외가 발생
         * -> application.properties에서 spring.main.allow-bean-definition-overriding 설정값을 true로 변경하면 예외 발생 X
         * @return
         */
//        @Bean(name = "memoryMemberRepository")
//        public MemberRepository memberRepository() {
//                return new MemoryMemberRepository();
//        }
}
