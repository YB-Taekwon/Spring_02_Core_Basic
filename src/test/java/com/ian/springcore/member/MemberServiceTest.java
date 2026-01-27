package com.ian.springcore.member;

import com.ian.springcore.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    //    MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    @BeforeEach
    void setUp() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void joinAndFindMember() {
        // given
        Member member = new Member(1L, "name", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());

        // then
        assertThat(findMember).isEqualTo(member);
    }
}