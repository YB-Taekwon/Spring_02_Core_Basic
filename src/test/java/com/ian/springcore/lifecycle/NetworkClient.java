package com.ian.springcore.lifecycle;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출: url= " + url);
        connect();
        call("초기화 연결 메시지");
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
}
