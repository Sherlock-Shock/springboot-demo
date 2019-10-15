package com.lee.springbootdemo.controller;

import com.lee.springbootdemo.dao.EmployeeDao;
import com.lee.springbootdemo.entities.Employee;
import com.lee.springbootdemo.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

@Controller
public class helloController {

    @Autowired
    EmployeeDao employeeDao;




    @ResponseBody
    @RequestMapping("/testMethod")
    public String testMethod(){

            return "ssss";
    }

    @RequestMapping("/sucess")
    public String testSucess(Map<String,Object> map){
        map.put("hello","hello world");
        return "sucess";
    }

    //@RequestMapping("/userLogin")
    @PostMapping(value = "/userLogin")
    public String userLogin(@RequestParam("username") String name, @RequestParam("psw") String psw,HttpServletRequest request, Map<String,Object> map ){
        HttpSession session1 = request.getSession();
        //System.out.println("waimian"+name+psw);
        if("zs".equals(name)){


            session1.setAttribute("username",name);
            System.out.println(session1.getAttribute("username"));

            return "redirect:/main.html";
        }else {
            map.put("msg","错误");
            System.out.println(name+psw);
            System.out.println("cuocucou");
            return "login";
        }


    }

    @GetMapping("empList")
    public String empList(Model model){

        Collection<Employee> emplist = employeeDao.getAll();
        System.out.println(emplist);
       // map.put("emplist",emplist);
        model.addAttribute("emplist",emplist);
        return "list";
    }


    @PutMapping("/testput")
    public String testput(@RequestParam("name") String name,@RequestParam("password") String password){
        System.out.println("姓名："+name+"密码："+password);
        return "testput.html";
    }

    @DeleteMapping("testdelete/{id}")
    public String testdelete(@PathVariable("id") String id){
        System.out.println(id);
        return "redirect:/testput.html";
    }

//    @GetMapping("/testput")
//    public String gotestput(){
//
//        return "testput";
//    }
}
