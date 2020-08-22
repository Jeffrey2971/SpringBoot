package com.jeffrey.controller;

import com.jeffrey.dao.DepartmentDao;
import com.jeffrey.dao.EmployeeDao;
import com.jeffrey.entities.Department;
import com.jeffrey.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 *
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    /**
     * 查询所有员工
     * @return 列表页面
     */
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        // 将结果放在请求域中
        model.addAttribute("emps", employees);
        // thymeleaf默认只拼接到classpath:/templates
        return "emp/list";
    }

    /**
     * 请求添加员工页面
     */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        // 到添加页面，查询所有的部门并在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    /**
     * 员工添加功能
     * SpringMVC自动将请求参数和入参对象的属性进行一一绑定，请求参数的名字必须和JavaBean入参对象里面的属性名一致
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        // 到员工列表中
        System.out.println("保存的员工信息：" + employee);
        // 保存员工数据
        employeeDao.save(employee);
        // redirect：表示重定向到一个地址 /表示当前项目路径
        // forward：表示转发到一个地址
        return "redirect:/emps";
    }

    /**
     * 修改员工信息页面，查出当前员工并在页面回显
     */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        // 查出部门并回显
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);

        // 回到修改页面（add页面是双用的，编辑和添加）
        return "emp/add";
    }

    /**
     * 修改员工信息，需要添加员工id
     */
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("修改的员工数据" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 删除员工
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
