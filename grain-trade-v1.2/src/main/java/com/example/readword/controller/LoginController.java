package com.example.readword.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Qin
 * @version 1.0
 * @description
 * @since 2020/8/14
 */
@Controller
@Slf4j
@Api(value = "登陆")
@RequestMapping(value = "/user")
public class LoginController {


    @RequestMapping(value = "/login")
    public String login(String username, String password, Model model) {
        System.out.println("=============");
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //主体提交登录请求到SecurityManager
            currentUser.login(token);
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("msg", "密码不正确");
        } catch (UnknownAccountException uae) {
            model.addAttribute("msg", "账号不存在");
        } catch (AuthenticationException ae) {
            model.addAttribute("msg", "状态不正常");
        }
        if (currentUser.isAuthenticated()) {
            System.out.println("认证成功");
            model.addAttribute("username", username);
            return "success";
        } else {
            token.clear();
            return "login";
        }
    }

}
