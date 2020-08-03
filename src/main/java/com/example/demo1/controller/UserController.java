package com.example.demo1.controller;

import com.example.demo1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * FileName:UserController
 * Author:   马梦娇
 * Date:     2020/7/30,16:40
 * Version:
 * 登录状态，如果权限为管理员可显示和修改
 */
@Controller
public class UserController {
    private static final String adminPage = "adminPage";

    //    @RequestMapping("/adminPage/{userId}")
//    public String historySignIn(HttpServletRequest request, @PathVariable String userID) {
    @RequestMapping("/adminPage")
    public String queryAllUser(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("urf-8");
        UserService userService = new UserService();
        request.setAttribute("userList", userService.queryAllUser());
        return adminPage;
    }
}
