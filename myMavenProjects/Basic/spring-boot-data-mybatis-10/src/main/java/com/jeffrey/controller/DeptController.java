package com.jeffrey.controller;

import com.jeffrey.bean.Department;
import com.jeffrey.bean.Employee;
import com.jeffrey.mapper.DepartmentMapper;
import com.jeffrey.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/dept/{id}")
    public Object getDepartment(@PathVariable("id") Integer id){
        Department deptById = departmentMapper.getDeptById(id);
        if(deptById == null){
            return "没有查到相关数据！";
        }else{
            return deptById;
        }
    }


    @GetMapping("/dept")
    public Department insertDept(Department department){
        departmentMapper.insertDept(department);
        return department;

    }

    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id){
        return employeeMapper.getEmpById(id);
    }

}
