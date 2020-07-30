package com.example.demo1.mapper;

import com.example.demo1.model.user;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FileName:userMapper
 * Author:   马梦娇
 * Date:     2020/7/29,17:06
 * Version:
 * 通过@Mapper标记，spring帮助生成实现类，并生成bean
 */
@Repository
@Mapper
public interface UserMapper {
    /**
     * 增加用户，输入id,password，权限为一般用户，返回更改条数
     */
    @Insert("INSERT TO user(id,password,authority) VALUES(#{id},#{password})")
    int add(user user);

    /**
     * 输入id，删除用户
     */
    @Delete("DELETE FROM user WHERE id=#{id}")
    int delById(@Param("id") String id);

    /**
     * 更改用户信息，根据id,修改密码，返回更改条数
     */
    @Update("UPDATE user SET password=#{password} WHERE id=#{id}")
    int updateById(user user);

    /**
     * 输入id，返回用户信息
     * */
    @Select("SELECT * FROM user WHERE id=#{id}")
    user queryById(@Param("id") String id);

    /**
     * 查询所有用户信息
     */
    @Select("SELECT * FROM user")
    List<user> queryAllUser();

}
