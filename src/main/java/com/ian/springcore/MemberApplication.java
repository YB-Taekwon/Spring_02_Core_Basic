package com.ian.springcore;

import com.ian.springcore.member.Grade;
import com.ian.springcore.member.Member;
import com.ian.springcore.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 메인 클래스를 이용한 순수 자바 테스트 (JUnit 사용 X)
 */
public class MemberApplication {

    public static void main(String[] args) {
        /**
         * 순수 자바 -> 스프링
         */
//        MemberService memberService = new MemberServiceImpl();
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ctx.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "name", Grade.VIP);

        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());

        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
