package com.ian.springcore.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: 사용자 A가 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB: 사용자 B가 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자 A의 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); // 10000이 나와야 하는데 싱글톤으로 인해 마지막 변경 사항인 20000이 나옴

        Assertions.assertThat(price).isEqualTo(20000);
    }

    @Configuration
    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}