package com.axy.zhiyu.service.impl;

import com.axy.zhiyu.bean.Bank;
import com.axy.zhiyu.bean.Bill;
import com.axy.zhiyu.bean.User;
import com.axy.zhiyu.commonutils.*;
import com.axy.zhiyu.mapper.BankMapper;
import com.axy.zhiyu.mapper.BillMapper;
import com.axy.zhiyu.mapper.UserMapper;
import com.axy.zhiyu.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${config.salt}")
    private String salt;

    @Value("${config.tokenKey}")
    private String tokenKey;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BillMapper billMapper;

    @Autowired
    private BankMapper bankMapper;

    @Autowired
    private IdWorker idWorker;

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

    /**
     * 注册用户
     * @param user
     */
    @Override
    public String registerUser(User user) {
        //查询邀请码
        Map<String, Object> map = new HashMap<>();
        map.put("invite_code", user.getInviteCode());
        List<User> users = this.userMapper.selectByMap(map);
        if (users == null || users.size() == 0) {
            return "邀请码错误！";
        }

        //用户注册
        user.setNickName(String.valueOf(idWorker.nextId()));
        user.setAvatar(null);
        user.setCreateTime(new Date());
        user.setInviteCode(Integer.valueOf(user.getNickName()));
        this.userMapper.insert(user);

        Bill bill = new Bill();
        bill.setUserId(user.getId());
        this.billMapper.insert(bill);

        Bank bank = new Bank();
        bank.setUserId(user.getId());
        this.bankMapper.insert(bank);

        return "注册成功！";
    }
}
