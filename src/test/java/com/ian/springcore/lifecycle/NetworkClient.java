package com.ian.springcore.lifecycle;

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

    public void init() {
        System.out.println("NetworkClient.afterPropertiesSet");

        connect();
        call("초기화 연결 메시지");
    }

    public void close() {
        System.out.println("NetworkClient.destroy");

        disconnect();
    }
}
