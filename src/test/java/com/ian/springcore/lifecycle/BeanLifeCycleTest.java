package com.ian.springcore.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    void test() {
        ConfigurableApplicationContext cac = new AnnotationConfigApplicationContext(LifecycleConfig.class);

        NetworkClient bean = cac.getBean(NetworkClient.class);
        cac.close();
    }

    /**
     * 스프링에서 제공하는 빈 생명 주기 콜백 3가지 방법
     * - 인터페이스
     * - 설정 정보(Config, xml 등)에 초기화 메서드, 종료 메서드 지정
     * - @PostConstruct, @PreDestroy 애너테이션
     */
    @Configuration
    static class LifecycleConfig {

        /**
         * 스프링 빈의 이벤트 라이트 사이클 (싱글톤 패턴)
         * 1. 스프링 컨테니어 생성
         * 2. 스프링 빈 생성 (생성자 주입)
         * 3. 의존 관계 주입 (필드 주입, Setter 주입, 메서드 주입)
         * 4. 초기화 콜백 -> 빈이 생성되고 의존 관계 주입이 완료된 시점에 호출: 의존 관계 주입이 완료되어 사용이 가능함
         * 5. 사용
         * 6. 소멸 전 콜백 -> 빈이 소멸되기 직전에 호출
         * 7. 스프링 종료
         */

        /**
         * 설정 정보에 초기화, 종료 메서드 지정
         * - 메서드 이름을 자유롭게 줄 수 있음
         * - 스프링 빈이 스프링 코드에 의존하지 않음
         * - 코드가 아닌 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에서도 초기화 및 종료 메서드를 적용할 수 있음
         * <p>
         * @Bean에서 종료 메서드의 경우, (inferred)값이 기본값으로 등록되어 메서드 명이 close, shutdown일 경우 자동으로 호출 (추론)
         * -> 스프링 빈으로 등록하면 종료 메서드는 따로 지정하지 않아도 정상 동작
         */
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            /**
             * 스프링 빈 라이프 사이클
             * 1. 객체 생성 -> 생성자 주입
             * 2. 의존 관계 주입 -> 필드 주입, Setter 주입, 메서드 주입
             * <p>
             * 결과
             * 생성자 호출: url= null
             * connect: url = null
             * call: url= null message= 초기화 연결 메시지
             * => 빈의 값이 초기화 되기 전(의존성 주입 X)에 사용하여 연결 실패
             */
            NetworkClient networkClient = new NetworkClient(); // 1. 객체 생성
            networkClient.setUrl("http://localhost:8080/"); // 2. 의존성 주입

            return networkClient;
        }
    }
}
