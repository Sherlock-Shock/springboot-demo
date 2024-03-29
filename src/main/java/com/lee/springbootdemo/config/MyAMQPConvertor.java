package com.lee.springbootdemo.config;


import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAMQPConvertor {

    @Bean
    public MessageConverter MyMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
