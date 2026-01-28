package com.ian.springcore.order;

import com.ian.springcore.discount.DiscountPolicy;
import com.ian.springcore.member.Member;
import com.ian.springcore.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // @Service 내부에 @Component 애너테이션이 있기 때문에 컴포넌트 스캔으로 스프링 빈으로 자동 등록
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

    @Autowired
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

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
