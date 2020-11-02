package com.axy.zhiyu.controller;

import com.axy.zhiyu.bean.User;
import com.axy.zhiyu.commonutils.CookieUtil;
import com.axy.zhiyu.commonutils.JwtUtil;
import com.axy.zhiyu.commonutils.R;
import com.axy.zhiyu.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {

    @Value("${config.salt}")
    private String salt;

    @Value("${config.tokenKey}")
    private String tokenKey;

    @Autowired
    UserService userService;

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
        Map<String, Object> param = JwtUtil.decode(token, tokenKey, salt);
        user.setUsername((String) param.get("username"));
        user.setPassword((String) param.get("password"));
        user = userService.getUserInfo(user);
        return R.ok().data("userInfo", user);
    }

}
