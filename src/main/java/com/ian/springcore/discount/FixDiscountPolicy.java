package com.ian.springcore.discount;

import com.ian.springcore.member.Grade;
import com.ian.springcore.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

    private int fixDiscountPrice = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP)
            return fixDiscountPrice;

        return 0;
    }
}
