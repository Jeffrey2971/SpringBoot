package com.jeffrey.mapper;

import com.jeffrey.bean.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapper {

    @Select("select * from employee where id=#{id}")
    Employee findEmployeeById(Integer id);

    @Insert("insert into employee (lastName, email, gender, d_id)values(#{lastName}, #{email}, #{gender}, #{d_id})")
    void saveEmployee(Employee employee);

}
