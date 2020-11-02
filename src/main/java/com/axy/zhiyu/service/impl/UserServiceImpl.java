package com.axy.zhiyu.service.impl;

import com.axy.zhiyu.bean.User;
import com.axy.zhiyu.commonutils.CodecUtils;
import com.axy.zhiyu.commonutils.CookieUtil;
import com.axy.zhiyu.commonutils.JwtUtil;
import com.axy.zhiyu.commonutils.WebConst;
import com.axy.zhiyu.mapper.UserMapper;
import com.axy.zhiyu.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${config.salt}")
    private String salt;

    @Value("${config.tokenKey}")
    private String tokenKey;

    @Override
    public User login(User user, HttpServletRequest request, HttpServletResponse response) {
        // 加密密码
        String password = CodecUtils.md5Hex(user.getPassword(), salt);
        user.setPassword(password);
        // 获取用户信息
        user = getUserInfo(user);
        if (user != null) {
            // 生成并返回token
            Map<String, Object> param = new HashMap<>();
            param.put("id", user.getId());
            param.put("username", user.getUsername());
            param.put("password", user.getPassword());
            String token = JwtUtil.encode(tokenKey, param, salt);
            user.setToken(token);
            CookieUtil.setCookie(request, response, "token", token, WebConst.COOKIE_MAXAGE, false);
            return user;
        }
        return null;
    }

    @Override
    public User getUserInfo(User user) {
        // 查询用户信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", user.getUsername());
        queryWrapper.eq("password", user.getPassword());
        user = baseMapper.selectOne(queryWrapper);
        return user;
    }
}
