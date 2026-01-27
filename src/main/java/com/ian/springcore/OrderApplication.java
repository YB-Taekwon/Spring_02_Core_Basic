package com.ian.springcore;

import com.ian.springcore.member.Grade;
import com.ian.springcore.member.Member;
import com.ian.springcore.member.MemberService;
import com.ian.springcore.order.Order;
import com.ian.springcore.order.OrderService;

/**
 * 메인 클래스를 이용한 순수 자바 테스트 (JUnit 사용 X)
 */
public class OrderApplication {

    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Member member = new Member(1L, "name", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(), "item", 10000);

        System.out.println("order = " + order);
    }
}
