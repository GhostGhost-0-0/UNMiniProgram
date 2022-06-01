package com.zzx.domain.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.config.WxConfig;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.User;
import com.zzx.domain.mapper.UserMapper;
import com.zzx.domain.service.UserService;
import com.zzx.utils.HttpClientUtils;
import com.zzx.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-02-28 13:48:11
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private WxConfig wxConfig;

    @Override
    public ResponseResult getOpenId(String code) {
        Map<String, String> param = new HashMap<>();
        param.put("appid", wxConfig.getAppid());
        param.put("secret", wxConfig.getSecret());
        param.put("js_code", code);
        param.put("grant_type", wxConfig.getGrantType());
        //发送请求
        String wxResult = HttpClientUtils.doPost(wxConfig.getUrl(), param);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        //获取参数返回
        String sessionKey = jsonObject.getString("session_key").toString();
        String openId = jsonObject.getString("openid").toString();
        Map<String, String> result = new HashMap<>();
        result.put("session_key", sessionKey);
        result.put("openid", openId);
        return ResponseResult.okResult(result);
    }

    @Override
    public User login(User insertUser) {
        //根据返回的user实体类，判断用户是否是新用户，不是的话，更新登录时间，是的话，将用户信息存到数据库
        System.out.println(insertUser);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getOpenid, insertUser.getOpenid());
        User user = getOne(queryWrapper);
        if (user != null) {
            user.setUpdateTime(new Date());
            updateById(user);
            return user;
        } else {
            insertUser.setId(UuidUtils.getId());
            insertUser.setCreateTime(new Date());
            insertUser.setUpdateTime(new Date());
            save(insertUser);
            return insertUser;
        }
    }
}

