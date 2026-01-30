package com.ian.springcore.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 프로토타입 빈
 * 스프링 컨테이너에서 빈을 조회할 때 빈 생성 및 초기화 메서드가 실행됨
 * 스프링 컨테이너에 요청할 때마다 새로 생성
 * 스프링 컨테이너에서는 프로토타입 빈의 생성, 의존관계 주입, 초기화 까지만 관여
 * -> 생성한 빈을 클라이언트에 바로 반환하고, 이후 빈에 대한 책임은 클라이언트에 있음
 * ==> 종료 메서드가 호출되지 않음 (종료 메서드를 클라이언트가 직접 호출)
 */
public class PrototpyeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = context.getBean(PrototypeBean.class);

        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);

        System.out.println("singletonBean1 = " + prototypeBean1);
        System.out.println("singletonBean2 = " + prototypeBean2);

        // 프로토타입 빈은 요청마다 새로 만들기 때문에 동일한 인스턴스가 아님
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // 프로토타입 빈은 반환 이후 스프링이 관리하지 않기 때문에 클라이언트가 종료 메서드를 직접 실행
        prototypeBean1.destroy();
        prototypeBean2.destroy();
        context.close(); // 실행 X
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
