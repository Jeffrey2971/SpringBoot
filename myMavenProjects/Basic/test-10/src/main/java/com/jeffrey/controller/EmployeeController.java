package com.jeffrey.controller;

import com.jeffrey.bean.Department;
import com.jeffrey.bean.Employee;
import com.jeffrey.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeMapper employeeMapper;

    @RequestMapping("emp/{id}")
    public Object getEmployee(@PathVariable("id") Integer id){
        Employee employeeById = employeeMapper.findEmployeeById(id);
        if(employeeById == null){
            return "没有查询到任何结果！";
        }
        return employeeById;
    }

    @RequestMapping("/dept")
    public void saveEmployee(Employee employee){
        employeeMapper.saveEmployee(employee);
    }
}
