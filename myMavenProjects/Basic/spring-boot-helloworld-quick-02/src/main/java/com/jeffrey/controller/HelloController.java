package com.jeffrey.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*@Controller
@ResponseBody // 这个类的所有方法返回的数据直接响应给浏览器，如果是对象则转为json数据*/
@RestController // Spring4.2
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "HelloWorldQuick";
    }

    // RestApi方式

}
