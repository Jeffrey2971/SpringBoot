package com.jeffrey.cache.service;

import com.jeffrey.cache.bean.Employee;
import com.jeffrey.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emp", cacheManager = "employeeCacheManager")
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 一、搭建基本环境
     * 1、导入数据库文件 创建出department和employee表
     * 2、创建javaBean封装数据
     * 3、整合MyBatis操作数据库
     * 1.配置数据源信息
     * 2.使用注解版的MyBatis；
     * 1）、@MapperScan指定需要扫描的mapper接口所在的包
     * 二、快速体验缓存
     * 步骤：
     * 1、开启基于注解的缓存 @EnableCaching
     * 2、标注缓存注解即可
     *
     * @Cacheable
     * @CacheEvict
     * @CachePut 默认使用的是ConcurrentMapCacheManager==ConcurrentMapCache；将数据保存在  ConcurrentMap<Object, Object>中
     * 开发中使用缓存中间件；redis、memcached、ehcache；
     * 三、整合redis作为缓存
     * Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。
     * 1、安装redis：使用docker；
     * 2、引入redis的starter
     * 3、配置redis
     * 4、测试缓存
     * 原理：CacheManager===Cache 缓存组件来实际给缓存中存取数据
     * 1）、引入redis的starter，容器中保存的是 RedisCacheManager；
     * 2）、RedisCacheManager 帮我们创建 RedisCache 来作为缓存组件；RedisCache通过操作redis缓存数据的
     * 3）、默认保存数据 k-v 都是Object；利用序列化保存；如何保存为json
     * 1、引入了redis的starter，cacheManager变为 RedisCacheManager；
     * 2、默认创建的 RedisCacheManager 操作redis的时候使用的是 RedisTemplate<Object, Object>
     * 3、RedisTemplate<Object, Object> 是 默认使用jdk的序列化机制
     * 4）、自定义CacheManager；
     */

    @Cacheable(value = {"emp"}/*, keyGenerator = "myKeyGenerator"*/)
    public Employee getEmployee(Integer id) {
        System.out.println("查询：" + id + "员工");
        return employeeMapper.getEmployeeById(id);
    }

    /**
     * @CachePut：既调用方法，又更新缓存数据；同步更新缓存 修改了数据库的某个数据，同时更新缓存；
     * 运行时机：
     * 1、先调用目标方法
     * 2、将目标方法的结果缓存起来
     * <p>
     * 测试步骤：
     * 1、查询1号员工；查到的结果会放在缓存中；
     * key：1  value：lastName：张三
     * 2、以后查询还是之前的结果
     * 3、更新1号员工；【lastName:zhangsan；gender:0】
     * 将方法的返回值也放进缓存了；
     * key：传入的employee对象  值：返回的employee对象；
     * 4、查询1号员工？
     * 应该是更新后的员工；
     * key = "#employee.id":使用传入的参数的员工id；
     * key = "#result.id"：使用返回后的id
     * @Cacheable的key是不能用#result 为什么是没更新前的？【1号员工没有在缓存中更新】
     */

    @CachePut(/*value = "emp", */key = "#result.id")
    public Employee updateEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);
        return employee;
    }

    /**
     * @CacheEvict：缓存清除 key：指定要清除的数据
     * allEntries = true：指定清除这个缓存中所有的数据
     * beforeInvocation = false：缓存的清除是否在方法之前执行
     * 默认代表缓存清除操作是在方法执行之后执行;如果出现异常缓存就不会清除
     * <p>
     * beforeInvocation = true：
     * 代表清除缓存操作是在方法运行之前执行，无论方法是否出现异常，缓存都清除
     */

    @CacheEvict(value = "emp"/*, key = "#id"*/, allEntries = true, beforeInvocation = true)
    public void deleteEmp(Integer id) {
        System.out.println("删除一个员：" + id);
        employeeMapper.deleteEmployee(id);
        // 自定义异常 //
        int i = 1 / 0;
    }

    /**
     * @Cacheing 注解中包含了 @Cacheable @CachePut 和 @evict
     * @param lastName
     * @return
     */
    @Caching(
            cacheable = {
                    @Cacheable(/*value = "emp", */key = "#lastName")
            },
            put = {
                    @CachePut(/*value = "emp", */key = "#result.id"),
                    @CachePut(/*value = "emp", */key = "#result.email")
            }

    )

    public Employee getEmployeeByUser(String lastName) {
        return employeeMapper.getEmployeeByLastName(lastName);

    }
}
