package com.ian.springcore.beanfind;

import com.ian.springcore.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 Bean 출력")
    void findAllBeans() {
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ctx.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 Bean 출력")
    void findApplicationBeans() {
        // Spring 6 이상 부터는 ApplicationContext 메서드가 노출되지 않아서 추가
        ConfigurableApplicationContext cac = (ConfigurableApplicationContext) ctx;
        BeanDefinitionRegistry bdr = (BeanDefinitionRegistry) cac.getBeanFactory();

        String[] beanDefinitionNames = cac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = bdr.getBeanDefinition(beanDefinitionName);

            // ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            // ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = cac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }
}
