package com.ian.springcore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LombokApplication {

    private String name;
    private int age;

    public static void main(String[] args) {
        LombokApplication app = new LombokApplication();
        app.setName("name");
        app.setAge(20);

        System.out.println("app = " + app);
    }
}
