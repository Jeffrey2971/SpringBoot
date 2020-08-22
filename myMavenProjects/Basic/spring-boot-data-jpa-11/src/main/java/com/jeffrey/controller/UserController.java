package com.jeffrey.controller;

import com.jeffrey.entity.User;
import com.jeffrey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public Object getUser(@PathVariable("id") Integer id){
        User one = this.userRepository.findOne(id);
        if(one == null){
            return "没有找到任何数据！";
        }
        return one;
    }

    @GetMapping("/user")
    public User insertUser(User user){
        User save = userRepository.save(user);
        return save;

    }
}
