package com.ian.springcore.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * BeanLifeCycleTest 결과
 * 생성자 호출: url= null
 * NetworkClient.afterPropertiesSet
 * connect: url = http://localhost:8080/
 * call: url= http://localhost:8080/ message= 초기화 연결 메시지
 * NetworkClient.destroy
 * disconnect: url = http://localhost:8080/
 */
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출: url= " + url);
//        connect();
//        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 네트워크 연결 시 호출
    public void connect() {
        System.out.println("connect: url = " + url);
    }

    public void call(String message) {
        System.out.println("call: url= " + url + " message= " + message);
    }

    // 네트워크 연결 해제 시 호출
    public void disconnect() {
        System.out.println("disconnect: url = " + url);
    }

    /**
     * 인터페이스
     * implements InitializingBean, DisposableBean
     * - InitializingBean: 빈 초기화
     * - DisposableBean: 빈 소멸
     * ※ 스프링에 지나치게 의존적인 초기 방식으로 현재는 잘 사용하지 않음
     */
    // 의존 관계 주입이 끝나면 호출
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//
//        connect();
//        call("초기화 연결 메시지");
//    }

    // 빈 소멸 전 호출
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//
//        disconnect();
//    }

    /**
     * @PostConstruct, @PreDestroy 애너테이션 사용
     * - 최신 스프링에서 가장 권장하는 방식
     * - 자바 표준으로 스프링에 종속적이지 않아 다른 프로그램에서도 잘 동작함
     * - 컴포넌트 스캔과 가장 좋은 조합
     * ※ 단, 외부 라이브러리에는 적용을 못 하기 때문에 이같은 경우에는 설정 정보에 빈 등록 메서드와 종료 메서드를 지정하는 방식을 사용
     */
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.afterPropertiesSet");

        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.destroy");

        disconnect();
    }
}
