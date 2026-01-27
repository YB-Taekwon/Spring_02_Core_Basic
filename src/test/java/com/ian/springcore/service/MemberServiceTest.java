package com.ian.springcore.service;

import com.ian.springcore.member.Grade;
import com.ian.springcore.member.Member;
import com.ian.springcore.member.MemberService;
import com.ian.springcore.member.MemberServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

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