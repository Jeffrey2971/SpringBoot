package com.jeffrey.cache;

import com.jeffrey.cache.bean.Employee;
import com.jeffrey.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class SpringBootCache01ApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate; // 操作 k - v 都是字符串的

    @Autowired
    RedisTemplate redisTemplate; // k - v 都是对象

    @Autowired
    RedisTemplate<Object, Employee> empRedisTemplate;

    /**
     * 保存字符串
     */
    @Test
    public void saveValue(){
        stringRedisTemplate.opsForValue().append("msg", "hello");
        redisTemplate.opsForValue().append("msg", " World");
    }

    /**
     * 读取字符串
     */
    @Test
    public void getValue(){
        String msg_1 = stringRedisTemplate.opsForValue().get("msg");
        Object msg_2 = redisTemplate.opsForValue().get("msg");
        System.out.println(msg_1);
        System.out.println(msg_2);
    }

    /**
     * 保存列表
     */
    @Test
    public void saveList(){
        stringRedisTemplate.opsForList().leftPush("myList", "1");
        redisTemplate.opsForList().leftPush("myList", 2);
    }

    /**
     * 读取列表
     */
    @Test
    public void popList(){
        String myList_1 = stringRedisTemplate.opsForList().leftPop("myList");
        Object myList_2 = redisTemplate.opsForList().rightPop("myList");
        System.out.println(myList_1);
        System.out.println(myList_2);
    }

    /**
     * 保存对象
     */
    @Test
    public void saveTarget(){
        Employee employee = employeeMapper.getEmployeeById(1);
        // 默认如果保存对象使用 jdk 序列化机制，序列化后保存的数据将会保存到 Redis 中
        // redisTemplate.opsForValue().set("emp_1", employee);
        // stringRedisTemplate.opsForValue().set("emp_1", String.valueOf(employee));
        // 将数据以 Json 方式保存
        // 1、自行将对象转换为 json
        // 2、RedisTemplate 默认的序列化规则
        // 改变默认的序列化规则
        empRedisTemplate.opsForValue().set("emp_2", employee);
    }

    /**
     * 根据 id 查询用户
     */
    @Test
    void contextLoads() {
        Employee employee = employeeMapper.getEmployeeById(1);
        System.out.println(employee);
    }

}
