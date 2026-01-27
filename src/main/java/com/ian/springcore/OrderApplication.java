package com.ian.springcore;

import com.ian.springcore.member.Grade;
import com.ian.springcore.member.Member;
import com.ian.springcore.member.MemberService;
import com.ian.springcore.order.Order;
import com.ian.springcore.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 메인 클래스를 이용한 순수 자바 테스트 (JUnit 사용 X)
 */
public class OrderApplication {

    public static void main(String[] args) {
        /**
         * 순수 자바 -> 스프링
         */
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = ctx.getBean("orderService", OrderService.class);
        MemberService memberService = ctx.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "name", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(), "item", 10000);

        System.out.println("order = " + order);
    }
}
