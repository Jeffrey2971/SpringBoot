package com.jeffrey.cache.service;

import com.jeffrey.cache.bean.Department;
import com.jeffrey.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Qualifier("departmentCacheManager")
    @Autowired
    RedisCacheManager departmentCacheManager;

    /**
     * 缓存的数据能存入 Redis 但是第二次从缓存中查询时就不能序列化回来，
     * 这是因为存储的是 dept 的 json 数据，CacheManager 默认使用的是
     * RedisTemplate<Object, Employee> empRedisTemplate 操作的 Redis
     * @param id
     * @return
     */
    /*@Cacheable(value = "dept", cacheManager = "departmentCacheManager")
    public Department getDepartmentMapper(Integer id){
        System.out.println("查询部门：" + id);
        Department department = departmentMapper.getDeptById(id);
        return department;
    }*/

    public Department getDepartmentMapper(Integer id) {
        System.out.println("查询部门：" + id);
        Department department = departmentMapper.getDeptById(id);

        // 获取某个缓存
        Cache dept = departmentCacheManager.getCache("dept");
        dept.put("dept:1", department);

        return department;
    }
}
