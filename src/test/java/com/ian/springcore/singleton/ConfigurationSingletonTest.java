package com.ian.springcore.singleton;

import com.ian.springcore.AppConfig;
import com.ian.springcore.member.MemberRepository;
import com.ian.springcore.member.MemberServiceImpl;
import com.ian.springcore.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberRepository = " + memberRepository);
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);

        Assertions.assertThat(memberRepository1).isSameAs(memberRepository);
        Assertions.assertThat(memberRepository2).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        /**
         * @Configuration: 해당 애너테이션이 붙은 config 파일을 CGLIB로 복제하여 스프링 빈으로 등록
         * 스프링에서 CGLIB 라는 바이트 코드 조작 라이브러리를 사용하여 AppConfig 클래스를 상속받은 임의의 다른 클래스를 생성하고,
         * 새로 생성한 클래스를 스프링 빈으로 등록 => 해당 클래스가 싱글톤이 보장되도록 해줌
         * <p>
         * CGLIB 예상 코드
         * if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) {
         *     return 스프링 컨테이너에서 찾아서 반환;
         * } else { //스프링 컨테이너에 없으면
         *     기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
         *     return 반환
         * }
         */
        System.out.println("bean = " + bean.getClass()); // bean = class com.ian.springcore.AppConfig$$SpringCGLIB$$0
    }
}
