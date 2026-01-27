package com.ian.springcore.order;

public class Order {

    private Long memberId;
    private String productName;
    private int productPrice;
    private int discountPrice;

    public Order(Long memberId, String productName, int productPrice, int discountPrice) {
        this.memberId = memberId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice() {
        return productPrice - discountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
