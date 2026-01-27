package com.ian.springcore.order;

import com.ian.springcore.discount.DiscountPolicy;
import com.ian.springcore.member.Member;
import com.ian.springcore.member.MemberRepository;
import com.ian.springcore.member.MemberService;

public class OrderServiceImpl implements OrderService {


    /**
     * - DIP 위반: OrderServiceImpl가 DiscountPolicy 뿐만 아니라 구현체에도 의존하고 있음
     * - OCP 위반: FixDiscountPolicy를 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl의 코드 또한 수정 필요
     */
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String productName, int productPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, productPrice);

        return new Order(member.getId(), productName, productPrice, discountPrice);
    }
}
