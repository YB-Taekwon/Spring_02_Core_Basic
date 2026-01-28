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
 */
@Configuration
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
