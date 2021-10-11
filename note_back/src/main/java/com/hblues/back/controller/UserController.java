package com.hblues.back.controller;

import com.hblues.back.pojo.User;
import com.hblues.back.response.Response;
import com.hblues.back.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("api/register")
    @ResponseBody
    public String register(@RequestBody User user) {
        String userName = user.getUsername();
        userName = HtmlUtils.htmlEscape(userName);
        user.setUsername(userName);
        String password = user.getPassword();

        boolean isExist = userService.isExist(userName);
        if (isExist) {
            return "用户名已存在";
        }

        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithm = "md5";
        String pwdAfterHash = new SimpleHash(algorithm, password, salt, times).toString();
        user.setSalt(salt);
        user.setPassword(pwdAfterHash);
        userService.addUser(user);

        return "创建成功";
    }

    @CrossOrigin
    @PostMapping("api/login")
    @ResponseBody
    public Response login(@RequestBody User user) {
        String username = user.getUsername();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, user.getPassword());

        try {
            subject.login(usernamePasswordToken);
            return new Response(200, "success", usernamePasswordToken);
        } catch (AuthenticationException e) {
            return new Response(500, "failure", null);
        }
    }
}
