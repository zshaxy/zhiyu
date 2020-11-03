package com.axy.zhiyu.service;

import com.axy.zhiyu.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService extends IService<User> {

    User login(User user, HttpServletRequest request, HttpServletResponse response);

    User getUserInfo(User user);

    String registerUser(User user);
}
