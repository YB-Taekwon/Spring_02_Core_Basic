package com.ian.springcore.discount;

import com.ian.springcore.member.Grade;
import com.ian.springcore.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_discount_success() {
        // given
        Member member = new Member(1L, "vip", Grade.VIP);

        // when
        int discountPrice = discountPolicy.discount(member, 10000);

        // then
        assertThat(discountPrice).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void basic_discount_failed() {
        // given
        Member member = new Member(2L, "basic", Grade.BASIC);

        // when
        int discountPrice = discountPolicy.discount(member, 10000);

        // then
        assertThat(discountPrice).isEqualTo(0);
    }
}