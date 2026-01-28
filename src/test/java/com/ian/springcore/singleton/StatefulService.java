package com.ian.springcore.singleton;

/**
 * 싱글톤은 무조건 상태가 저장되지 않는 무상태로 구현해야 함
 * 상태가 유지될 때 발생할 수 있는 문제
 * <p>
 * 무상태 설계
 * class StatelessService {
 *     // price 필드 제거 및 값 저장 X, 바로 반환
 *     public int order(String name, int price) {
 *         return price;
 *     }
 * }
 */
public class StatefulService {

    private int price; // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name + " + name + " price = " + price);
        this.price = price; // 여기서 문제 발생 -> StatefulServiceTest에서 확인
    }

    public int getPrice() {
        return price;
    }
}
