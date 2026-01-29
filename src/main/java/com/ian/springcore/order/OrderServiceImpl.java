package com.ian.springcore.order;

import com.ian.springcore.discount.DiscountPolicy;
import com.ian.springcore.member.Member;
import com.ian.springcore.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // @Service 내부에 @Component 애너테이션이 있기 때문에 컴포넌트 스캔으로 스프링 빈으로 자동 등록
@RequiredArgsConstructor // @RequiredArgsConstructor: final 필드의 생성자 주입 코드를 대신 작성해줌
public class OrderServiceImpl implements OrderService {


    /**
     * - DIP 위반: OrderServiceImpl가 DiscountPolicy 뿐만 아니라 구현체에도 의존하고 있음
     * - OCP 위반: FixDiscountPolicy를 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl의 코드 또한 수정 필요
     */
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /**
     * 의존성 주입 방식: 생성자 주입
     * - 생성자 호출 시점에 딱 1번만 호출되는 것이 보장됨
     * - 불변, 필수 의존 관계에 주로 사용
     * 1. 사용할 필드 선언
     * 2. 생성자 호출로 필드 값 초기화 및 객체 생성
     * 3. 필드가 final이기 때문에 한 번 초기화 된 값을 변경할 수 없음
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

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

    /**
     * 의존성 주입 방식: setter 주입
     * - 선택, 변경 가능성이 있는 의존 관계에서 사용
     * 1. 사용할 필드 선언
     * 2. 생성자 호출로 필드의 빈 객체 생성
     * 3. setter로 의존 관계 주입
     */
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    /**
     * 의존성 주입 방식: 필드 주입
     * - 의존 관계가 주입되지 않은 상태로 객체가 만들어지기 때문에 기본 생성자 호출 시 NPE 발생 가능성이 높음
     * - 외부에서 필드에 직접 의존 관계를 주입할 수 있는 방법이 없음 -> 외부에서 의존 관계 주입 시 setter를 사용해야 함
     * 1. 사용할 필드 선언
     * 2. 생성자 호출로 필드의 빈 객체 생성
     * 3. 사용할 의존성 주입
     */
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private DiscountPolicy discountPolicy;
}
