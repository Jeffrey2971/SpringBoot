package com.jeffrey.cache.mapper;

import com.jeffrey.cache.bean.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {

    /**
     * 查询
     * @param id
     * @return
     */
    @Select("select * from employee where id=#{id}")
    public Employee getEmployeeById(Integer id);

    @Update("update employee set lastName=#{lastName}, email=#{email}, gender=#{gender}, d_id=#{dId} where id=#{id}")
    public void updateEmployee(Employee employee);

    @Delete("delete from employee where id=#{id}")
    public void deleteEmployee(Integer id);

    @Insert("insert into employee(lastName, email, gender, d_id)Values(#{lastName}, #{email}, #{gender}, #{dId}")
    public void insertUser(Employee employee);

    @Select("select * from employee where lastName=#{lastName}")
    public Employee getEmployeeByLastName(String lastName);
}
