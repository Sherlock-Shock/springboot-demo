package com.lee.springbootdemo.controller;


import com.lee.springbootdemo.Mapper.ManagerMapper;
import com.lee.springbootdemo.Service.ManagerService;
import com.lee.springbootdemo.entities.manager;
import com.mysql.cj.xdevapi.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestSQLController {
    @Resource
    @Autowired
    ManagerMapper managerMapper;

    @Resource
    @Autowired
    ManagerService managerService;




    @GetMapping("/queryall")
    public List<manager> queryAll(){


        return managerService.queryAllManager();
    }

    @GetMapping("/queryManagerByIdTest/{mid}")
    public manager queryManagerByIdTest(@PathVariable int mid){

        //System.out.println(managerMapper.queryAll(mid));
        return managerService.queryManagerByIdTest(mid);
    }



}
