package com.axy.zhiyu.service.impl;

import com.axy.zhiyu.bean.User;
import com.axy.zhiyu.mapper.UserMapper;
import com.axy.zhiyu.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getUserInfo(User user) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", user.getPassword());
        return baseMapper.selectOne(queryWrapper);
    }
}
