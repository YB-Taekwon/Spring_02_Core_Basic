package com.ian.springcore.order;

import com.ian.springcore.member.Member;

public interface OrderService {

    Order createOrder(Long memberId, String productName, int productPrice);
}
