package com.lee.springbootdemo.Service;


import com.lee.springbootdemo.entities.*;
import com.lee.springbootdemo.Mapper.ManagerMapper;

import com.alibaba.fastjson.*;
import com.lee.springbootdemo.util.FastJSONUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ManagerService {

    @Resource
    @Autowired
    ManagerMapper managerMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;//k-v都是字符串



    @Cacheable(cacheNames = "manager")
    public manager queryManagerByIdTest(int mid){

        System.out.println("查询："+mid);

        return managerMapper.queryManagerByIdTest(mid);

    }

    public List<manager> queryAllManager() {



        String managerJson = stringRedisTemplate.opsForValue().get("manager");//先查缓存
        //List<manager> managerList = JSONObject.parseArray(managerJson,manager.class);

        if(null==managerJson) {    //没有就查数据库
            synchronized (this) {  //放行一个
                managerJson = stringRedisTemplate.opsForValue().get("manager");
                if(null==managerJson) {

                    List<manager> managers = managerMapper.queryAll();
                    managerJson= FastJSONUtil.bean2json(managers);

                    System.out.println("--------在数据库中查询" + managerJson);

                    stringRedisTemplate.opsForValue().set("manager",managerJson);

                }
            }
        }else{
            System.out.println("在缓存中获取..."+managerJson);
        }


        return FastJSONUtil.json2beans(managerJson,manager.class);
    }


//    @RabbitListener(queues = "lee")
//    public void testRabbitmq(Map<String,String> map){
//        System.out.println("监听到消息队列..."+map);
//    }

}
