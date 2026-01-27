package com.ian.springcore;

import com.ian.springcore.member.Grade;
import com.ian.springcore.member.Member;
import com.ian.springcore.member.MemberService;

/**
 * 메인 클래스를 이용한 순수 자바 테스트 (JUnit 사용 X)
 */
public class MemberApplication {

    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "name", Grade.VIP);

        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());

        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
