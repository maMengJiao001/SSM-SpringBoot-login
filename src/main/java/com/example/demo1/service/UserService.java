package com.example.demo1.service;

import com.example.demo1.mapper.UserMapper;
import com.example.demo1.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FileName:userService
 * Author:   马梦娇
 * Date:     2020/7/29,22:59
 * Version:
 * service服务层
 * 复杂的业务逻辑处理层，对mapper对象和类对象进行调用和装配
 * 缓存所在层
 *
 * @Cacheable将查询结果缓存到redis中，（key="#p0"）指定传入的第一个参数作为redis的key。
 * @CachePut，指定key，将更新的结果同步到redis中
 * @CacheEvict，指定key，删除缓存数据，allEntries=true,方法调用后将立即清除缓存
 */
@Service
@CacheConfig(cacheNames = "users")
public class UserService {
    @Autowired
    UserMapper userMapper;

    @CachePut(key = "#p0")
    public int add(user user) {
        if (userMapper.queryById(user.getId()) == null) {
            userMapper.add(user);
            return 1;
        } else {
            return 0;
        }
    }

    @CacheEvict(key = "#p0", allEntries = true)
    public int delById(String id) {
        if (userMapper.queryById(id).equals("0")) {
            return 0;
        } else {
            userMapper.delById(id);
            return 1;
        }
    }

    @CachePut(key = "#p0")
    public int update(user user) {
        if (userMapper.queryById(user.getId()).equals("0")) {
            userMapper.updateById(user);
            return 1;
        } else {
            return 0;
        }
    }

    @Cacheable(key = "#p0")
    public user queryById(String id) {
        return userMapper.queryById(id);
    }

    @Cacheable(key = "#p0")
    public List<user> queryAllUser() {
        return userMapper.queryAllUser();
    }


}
