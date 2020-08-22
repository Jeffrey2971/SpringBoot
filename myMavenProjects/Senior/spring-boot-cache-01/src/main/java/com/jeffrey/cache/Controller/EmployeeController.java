package com.jeffrey.cache.Controller;

import com.jeffrey.cache.bean.Employee;
import com.jeffrey.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * 查询一个员工
     * @param id
     * @return
     */
    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        Employee emp = employeeService.getEmployee(id);
        return emp;
    }

    /**
     * 更新一个员工
     * @param employee
     * @return
     */
    @GetMapping("/emp")
    public Employee updateEmployee(Employee employee){
        System.out.println("updateEmp：" + employee);
        Employee emp = employeeService.updateEmployee(employee);
        return emp;
    }

    /**
     * 删除一个员工
     */
    @DeleteMapping("/del/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeService.deleteEmp(id);
        return "Success";
    }

    /**
     * 根据姓名查询一个员工
     */
    @GetMapping("/emp/lastname/{lastName}")
    public Employee getEmployeeByLastName(@PathVariable("lastName") String lastName){
        return employeeService.getEmployeeByUser(lastName);
    }
}
