package com.ian.springcore.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // TYPE: 클래스 레벨
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
