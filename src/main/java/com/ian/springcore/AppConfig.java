package com.ian.springcore;

import com.ian.springcore.discount.DiscountPolicy;
import com.ian.springcore.discount.RateDiscountPolicy;
import com.ian.springcore.member.MemberRepository;
import com.ian.springcore.member.MemberService;
import com.ian.springcore.member.MemberServiceImpl;
import com.ian.springcore.member.MemoryMemberRepository;
import com.ian.springcore.order.OrderService;
import com.ian.springcore.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 각 객체의 관심사를 분리하고 애플리케이션의 전체 동작 방식을 구성하기 위해 구현 객체를 생성하고 연결하는 설정 클래스
 * <p>
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
@Configuration // 즉, Configuration을 붙이지 않으면 CGLIB로 복제한 config 파일이 아닌 실제 config 파일이 빈으로 등록되기 때문에 싱글톤 보장 X
public class AppConfig {

    /**
     * memberRepository, memberService, orderService로 인해 memberRepository가 총 3번 호출되어야 하는데
     * 스프링 컨테이너가 싱글톤을 보장하여 애플리케이션 실행 시 오직 1번만 호출되는 것을 확인할 수 있음
     */
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
