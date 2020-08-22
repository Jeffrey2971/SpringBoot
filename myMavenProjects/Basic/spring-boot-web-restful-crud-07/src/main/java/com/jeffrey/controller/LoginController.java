package com.jeffrey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 *
 */

@Controller
public class LoginController {

     // @PostMapping("/user/login")
     // @GetMapping
     // @PutMapping
     // @DeleteMapping
     @RequestMapping(value = "/user/login", method = RequestMethod.POST)

     public String login(
             @RequestParam("username") String username,
             @RequestParam("password") String password,
             Map<String, Object> maps, HttpSession session) {
        if (!StringUtils.isEmpty(username) && "664490254".equals(password)){
            // 登陆成功，为了防止表单重复提交，可重定向到主页
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        }else{
            // 登录失败
            maps.put("message", "用户名或密码错误！");
            return "login";
        }
    }
}
