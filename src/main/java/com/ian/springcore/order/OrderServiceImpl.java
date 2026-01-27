package com.ian.springcore.order;

import com.ian.springcore.discount.DiscountPolicy;
import com.ian.springcore.discount.FixDiscountPolicy;
import com.ian.springcore.member.Member;
import com.ian.springcore.member.MemberRepository;
import com.ian.springcore.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String productName, int productPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, productPrice);

        return new Order(member.getId(), productName, productPrice, discountPrice);
    }
}
