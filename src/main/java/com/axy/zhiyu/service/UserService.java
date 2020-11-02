package com.axy.zhiyu.service;

import com.axy.zhiyu.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {

    User getUserInfo(User user);
}
