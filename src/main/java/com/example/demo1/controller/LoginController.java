package com.example.demo1.controller;

import com.example.demo1.model.authority;
import com.example.demo1.model.user;
import com.example.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * FileName:LoginController
 * Author:   马梦娇
 * Date:     2020/8/1,11:41
 * Version:
 */
@Controller
public class LoginController {
    private final static String indexPage = "index";
    private final static String redirectIndexPage = "redirect:index";
    private final static String homePage = "homePage";
    private final static String adminPage = "adminPage";

    @Autowired
    UserService userService;

    @Autowired
    user user;

    @RequestMapping("/start")
    public String loginPage(HttpServletRequest request) {
        if (request.getSession().getAttribute("registState") != null) {
            request.setAttribute("registState", request.getSession().getAttribute("registState"));
            request.getSession().removeAttribute("registState");
        }
        return indexPage;
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, @RequestParam("userno") String id,
                        @RequestParam("password") String password) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        user = userService.queryById(id);
        if (user != null) {
            if (user.getPassword().trim().equals(password)) {
                if (user.getAuthority().equals(authority.general.toString())) {
                    request.getSession().setAttribute("user", user);
                    return homePage;
                }
                request.getSession().setAttribute("user", user);
                return adminPage;
            }
            request.setAttribute("loginError", "用户或密码错误");
            return indexPage;
        }
        request.setAttribute("loginError", "用户或密码错误");
        return indexPage;

    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request, @RequestParam("userno") String id,
                           @RequestParam("password") String password) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        user.setId(id);
        user.setPassword(password);
        user.setAuthority("general");
        if (userService.add(user) == 1) {
//            request.setAttribute("registerState", "注册成功！");
            request.getSession().setAttribute("user",user);
            return homePage;
        } else {
            request.setAttribute("registerState", "注册失败！重新测试或联系管理员");
            return redirectIndexPage;
        }
    }
}
