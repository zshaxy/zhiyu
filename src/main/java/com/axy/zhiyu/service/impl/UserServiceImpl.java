package com.axy.zhiyu.service.impl;

import com.axy.zhiyu.bean.User;
import com.axy.zhiyu.commonutils.CodecUtils;
import com.axy.zhiyu.commonutils.JwtUtils;
import com.axy.zhiyu.mapper.UserMapper;
import com.axy.zhiyu.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.InputStream;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${config.salt}")
    private String salt;

    @Override
    public User getUserInfo(User user) {
        // 加密密码
        String password = CodecUtils.md5Hex(user.getPassword(), salt);
        user.setPassword(password);
        // 查询用户信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", user.getPassword());
        user = baseMapper.selectOne(queryWrapper);
        // 生成token
        String jwtToken = JwtUtils.getJwtToken(user.getId(), user.getUsername());
        user.setToken(jwtToken);
        return user;
    }
}
