package com.ian.springcore.autowired.allbean;

import com.ian.springcore.AutoAppConfig;
import com.ian.springcore.discount.DiscountPolicy;
import com.ian.springcore.member.Grade;
import com.ian.springcore.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        // given
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "name", Grade.VIP);

        // when
        int fixDiscountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");

        // then
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(fixDiscountPrice).isEqualTo(1000);
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    /**
     * Map으로 모든 빈을 받아온 후 동적으로 필요한 빈을 주입하여 사용할 수 있음
     */
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

            // policyMap = {fixDiscountPolicy=com.ian.springcore.discount.FixDiscountPolicy@49798e84, rateDiscountPolicy=com.ian.springcore.discount.RateDiscountPolicy@6ed06f69}
            System.out.println("policyMap = " + policyMap);
            // policies = [com.ian.springcore.discount.FixDiscountPolicy@49798e84, com.ian.springcore.discount.RateDiscountPolicy@6ed06f69]
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
