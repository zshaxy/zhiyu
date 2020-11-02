package com.axy.zhiyu.controller;

import com.axy.zhiyu.bean.User;
import com.axy.zhiyu.commonutils.R;
import com.axy.zhiyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("login")
    public R Login(User user) {
        User userInfo = userService.getUserInfo(user);
        return R.ok().data("userInfo", userInfo);
    }

}
