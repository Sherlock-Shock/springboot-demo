package com.lee.springbootdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.springbootdemo.Mapper.ManagerMapper;
import com.lee.springbootdemo.entities.manager;


import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {
    @Resource
    @Autowired
    ManagerMapper managerMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;//k-v都是字符串

    @Autowired
    RedisTemplate redisTemplate;//k-v都是对象

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    public void test1(){
        //stringRedisTemplate.opsForValue().append("msg","ssssss");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
    }

    @Test
    public void test2() throws JsonProcessingException {

        List<manager> managers = managerMapper.queryAll();

        String managerJson = stringRedisTemplate.opsForValue().get("manager");

        if(null==managerJson) {
            ObjectMapper objectMapper = new ObjectMapper();
            managerJson = objectMapper.writeValueAsString(managers);
            System.out.println("--------数据库中" + managerJson);
            stringRedisTemplate.boundValueOps("manager").set(managerJson);

        }else{
            System.out.println("缓存中...");
        }

//
//
//
//        redisTemplate.opsForValue().set("man1",manager);
//
//        System.out.println(msg);
    }


    @Test
    public void contextLoads() {
        System.out.println("123");
    }

    @Test
    public void testRabbitmq() {

        Map<String,String> map = new HashMap<>();
        map.put("msg1","hellospringboot");
        map.put("msg2","222");
        rabbitTemplate.convertAndSend("exchange.direct","lee",map);
    }

    @Test
    public void getRabbitmq() {
        Object o = rabbitTemplate.receiveAndConvert("lee");
        System.out.println(o.getClass());
        System.out.println(o);

    }

    @Test
    public void testMail() throws Exception{
//        简单邮件
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setSubject("开会时间");
//        simpleMailMessage.setText("7点开会");
//        simpleMailMessage.setTo("752750447@qq.com");
//        simpleMailMessage.setFrom("3028420200@qq.com");



        //复杂邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setSubject("开会时间");
        mimeMessageHelper.setText("<b style='color:red;'>今晚7点开会</b>",true);
        mimeMessageHelper.setTo("752750447@qq.com");
        mimeMessageHelper.setFrom("3028420200@qq.com");

        mimeMessageHelper.addAttachment("1.jpg",new File("C:\\Users\\75275\\Pictures\\wallhaven-76x5dv.jpg"));


        javaMailSender.send(mimeMessage);

    }

}
