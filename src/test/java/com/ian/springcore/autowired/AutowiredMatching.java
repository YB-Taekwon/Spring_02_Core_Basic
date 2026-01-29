package com.ian.springcore.autowired;

import com.ian.springcore.AppConfig;
import com.ian.springcore.discount.DiscountPolicy;
import com.ian.springcore.discount.FixDiscountPolicy;
import com.ian.springcore.discount.RateDiscountPolicy;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AutowiredMatching {

    /**
     * 상황
     *
     * @Autowired의 경우 타입을 기반으로 빈을 조회하기 때문에 동일한 타입의 구현체가 2개 이상 빈으로 등록된 경우 중복 예외가 발생
     * - fixDiscountPolicy
     * - rateDiscountPolicy
     */
//    private final DiscountPolicy discountPolicy;
//
//    @Autowired // 타입 매칭 -> ac.getBean(DiscountPolicy.class)
//    public AutowiredMatching(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    /**
     * @Autowired 필드/파라미터 명 매칭
     * @Autowired는 타입 매칭을 시도하고, 빈이 여러 개가 있을 경우
     * 필드 이름(필드 주입), 파라미터 이름(생성자 주입, Setter 주입, 메서드 주입)으로 빈을 추가 매칭함
     */
    // 필드 이름 매칭 -> ac.getBean("rateDiscountPolicy", DiscountPolicy.class)
//    @Autowired
//    private DiscountPolicy rateDiscountPolicy;

    // 파라미터 이름 매칭 -> ac.getBean("fixDiscountPolicy", DiscountPolicy.class)
//    private final DiscountPolicy discountPolicy;
//
//    @Autowired
//    public AutowiredMatching(DiscountPolicy fixDiscountPolicy) {
//        this.discountPolicy = fixDiscountPolicy;
//    }

    /**
     * @Qualifier
     * Qualifier: 추가 구분자를 붙여주는 방식으로, 주입 시 추가적인 방법을 제공하는 것이지 빈 이름 자체를 변경하는 것은 아님
     * 1. @Qualifier끼리 매칭
     * 2. @Qualifier를 찾을 수 없으면 빈 이름으로 매칭
     * 3. NoSuchBeanDefinitionException 예외 발생
     */
//    private final DiscountPolicy discountPolicy;
//
//    @Autowired
//    public AutowiredMatching(@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    /**
     * @Primary
     * Primary: 우선순위를 지정하는 방식
     * - 빈으로 등록할 객체에만 @Primary 애너테이션을 붙이면 되며, 해당 애너테이션이 붙은 빈이 우선권을 갖게됨
     * @Primary -> RateDiscountPolicy
     */
    private final DiscountPolicy discountPolicy;

    @Autowired
    public AutowiredMatching(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    /* 단, 스프링의 경우 자동 빈 등록보다 수동 빈 등록이 우선권을 갖는 것처럼 상세하게 동작하는 것이 우선권을 갖기 때문에 @Primary보다 @Qualifier의 우선 순위가 높음*/
}
