package com.ian.springcore.singleton;

public class SingletonService {

    // 1. static 영역에 객체 인스턴스를 생성
    private static final SingletonService instance = new SingletonService();

    // 2. 오직 하나의 객체 인스턴스만 존재해야 하기 때문에 생성자를 private으로 선언하여 외부 호출을 막음
    private SingletonService() {}

    // 3. 인스턴스 사용을 위한 getInstance 메서드 제공 -> static 영역의 인스턴스를 반환하기 때문에 항상 같은 인스턴스 반환
    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
