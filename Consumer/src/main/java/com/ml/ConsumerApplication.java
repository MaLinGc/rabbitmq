package com.ml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerApplication {

    public static void main(String[] args) throws InterruptedException {
        AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("classpath:spring-rabbitmq.xml");
    }
}
