package com.axy.zhiyu.controller;

import com.axy.zhiyu.dao.User;
import com.axy.zhiyu.commonutils.JwtUtil;
import com.axy.zhiyu.commonutils.R;
import com.axy.zhiyu.config.tokenKey;
import com.axy.zhiyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("api/user")
@CrossOrigin
@EnableConfigurationProperties(tokenKey.class)
public class UserController {

    @Autowired
    private tokenKey tokenKey;

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public R Login(User user, HttpServletRequest request, HttpServletResponse response) {
        // 如果token中存在用户信息，就取出用户信息返回，否则查询数据库
        user = userService.login(user, request, response);
        if (user != null) {
            return R.ok().data("userInfo", user);
        }
        return R.error().code(6);
    }

    @PostMapping("getUserInfo")
    public R getUserInfo(User user, HttpServletRequest request, HttpServletResponse response) {
        String token = user.getToken();
        Map<String, Object> param = JwtUtil.decode(token, tokenKey.getTokenKey(), tokenKey.getSalt());
        user.setUsername((String) param.get("username"));
        user.setPassword((String) param.get("password"));
        user = userService.getUserInfo(user);
        return R.ok().data("userInfo", user);
    }

    /**
     * 用户注册
     */
    @PostMapping("register")
    public R registerUser(User user) {
        System.out.println(user.toString());
        String message = this.userService.registerUser(user);
        if (StringUtils.equals(message, "邀请码错误！")) {
            return R.error().message(message);
        } else if (StringUtils.equals(message, "用户已经存在！")) {
            return R.error().message(message);
        }
        return R.ok().message(message);

    }

}
