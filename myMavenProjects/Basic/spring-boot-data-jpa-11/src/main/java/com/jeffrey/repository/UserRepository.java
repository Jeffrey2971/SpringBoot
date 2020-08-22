package com.jeffrey.repository;

import com.jeffrey.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 集成JPARepository来完成对数据库的操作
public interface UserRepository extends JpaRepository<User,Integer> {

}
