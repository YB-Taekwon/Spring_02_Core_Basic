package com.ian.springcore;

import com.ian.springcore.discount.RateDiscountPolicy;
import com.ian.springcore.member.MemberService;
import com.ian.springcore.member.MemberServiceImpl;
import com.ian.springcore.member.MemoryMemberRepository;
import com.ian.springcore.order.OrderService;
import com.ian.springcore.order.OrderServiceImpl;
import org.springframework.context.annotation.Configuration;

/**
 * 각 객체의 관심사를 분리하고 애플리케이션의 전체 동작 방식을 구성하기 위해 구현 객체를 생성하고 연결하는 설정 클래스
 */
@Configuration
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
    }
}
