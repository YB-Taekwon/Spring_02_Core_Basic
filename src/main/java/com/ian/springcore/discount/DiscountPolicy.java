package com.ian.springcore.discount;

import com.ian.springcore.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);
}
