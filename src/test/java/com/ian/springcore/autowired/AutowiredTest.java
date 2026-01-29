package com.ian.springcore.autowired;

import com.ian.springcore.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

        /**
         * TestBean 빈 등록 실행 결과
         * noBean2 = null
         * noBean3 = Optional.empty
         */
    }

    static class TestBean {

        /**
         * 옵션 처리
         * Member는 빈이 아니기 때문에 자동 주입 대상을 찾을 수 없음
         */
        // @Autowired(required = false): 자동 주입 대상이 없을 경우 메서드 자체가 실행되지 않음
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // @Nullable: 자동 주입 대상이 없을 경우 null이 입력됨
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // Optional<T>: 자동 주입 대상이 없을 경우 Optional.empty가 입력됨
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }

}
