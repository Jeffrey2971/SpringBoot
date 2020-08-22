package com.jeffrey.controller;

import com.jeffrey.bean.Department;
import com.jeffrey.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentMapper departmentMapper;

    @RequestMapping("/dept/{id}")
    public Object dept(@PathVariable("id") Integer id){
        Department dept = departmentMapper.dept(id);
        if(dept == null){
            return "没有找到任何相关资源！";
        }
        return dept;
    }
}
