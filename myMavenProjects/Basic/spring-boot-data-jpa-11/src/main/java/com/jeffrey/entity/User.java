package com.jeffrey.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

// 使用JPA注解配置映射关系

@Table(name = "tbl_user") // @Table制定和哪个数据表进行对应，如果省略默认表名就是类名小写
@Entity // 告诉JPA这是一个实体类（和数据表映射的类）
public class User {

    @Id // 标注这是一个组件
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "last_name", length = 50) // 标注这是和数据表对应的一个列
    private String lastName;

    @Column // 省略默认为列明就是属性名
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
