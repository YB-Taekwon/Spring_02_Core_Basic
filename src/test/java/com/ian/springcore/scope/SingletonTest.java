package com.ian.springcore.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 싱글톤 빈
 * 스프링 컨테이너 생성 시점에 빈 생성 및 초기화 메서드가 실행됨
 * 스프링이 빈을 관리하며, 스프링이 종료되기 전까지 빈이 살아있음
 * -> 스프링 컨테이너에 같은 요청이 와도 동일한 인스턴스를 반환
 * ==> 스프링이 관리하기 때문에 종료 메서드가 실행됨
 */
public class SingletonTest {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = context.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = context.getBean(SingletonBean.class);

        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);

        assertThat(singletonBean1).isSameAs(singletonBean2);

        context.close();
    }

    @Scope("singleton")
    static class SingletonBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
