package com.ian.springcore.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = context.getBean(PrototypeBean.class);
        prototypeBean1.addCount();

        assertThat(prototypeBean1.count).isEqualTo(1);

        PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);
        prototypeBean2.addCount();

        assertThat(prototypeBean2.count).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        /**
         * 싱글톤 빈과 프로토타입 빈을 함께 사용할 경우 발생하는 문제점
         * 클라이언트 빈: 싱글톤 / 프로토타입 빈: 프로토타입
         * 1. 클라이언트 빈 생성 (싱글톤)
         * 2. 클라이언트에서 프로토타입 빈 요청
         * 3. 스프링 컨테이너에서 프로토타입 빈 반환
         * 4. 클라이언트 빈에 프로토타입 빈 의존성 주입 (싱글톤 빈은 생성 시점에 1번만 의존성을 주입받음)
         * -> 처음 생성된 프로토타입 빈이 유지됨 (이후 클라이언트에서 프로토타입 빈을 계속 요청해도 처음 생성된 프로토타입 빈만 사용)
         */
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean clientBean1 = context.getBean(ClientBean.class);
        int count1 = clientBean1.logic();

        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = context.getBean(ClientBean.class);
        int count2 = clientBean2.logic();

        assertThat(count2).isEqualTo(2);
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }


    @Scope("singleton")
    static class ClientBean {

        private final PrototypeBean prototypeBean;

        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();

            return prototypeBean.getCount();
        }
    }
}
