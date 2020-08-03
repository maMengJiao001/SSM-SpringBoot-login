package com.example.demo1.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * FileName:user
 * Author:   马梦娇
 * Date:     2020/7/29,13:11
 * Version:
 * 一般来说类名大小，这里是为了配合数据库名字，改为小写
 */
@Component
@Data
public class user implements Serializable {
    private int num;
    private String id;
    private String password;
    private String authority;
}
