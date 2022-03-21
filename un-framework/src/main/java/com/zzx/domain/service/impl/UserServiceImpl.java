package com.zzx.domain.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.config.WxConfig;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.User;
import com.zzx.domain.mapper.UserMapper;
import com.zzx.domain.service.UserService;
import com.zzx.utils.HttpClientUtils;
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
    public ResponseResult login(String openid, String avatar, String nickName, String sex, String city, String province, String country) {
        //根据返回的user实体类，判断用户是否是新用户，不是的话，更新登录时间，是的话，将用户信息存到数据库
        User user = getById(openid);
        if (user != null) {
            user.setCreateTime(new Date());
            updateById(user);
        } else {
            User insert_user = new User();
            insert_user.setOpenid(openid);
            insert_user.setAvatar(avatar);
            insert_user.setNickName(nickName);
            insert_user.setSex(sex);
            insert_user.setCity(city);
            insert_user.setProvince(province);
            insert_user.setCountry(country);
            insert_user.setCreateTime(new Date());
            insert_user.setCreateBy(insert_user.getId());
            save(insert_user);
        }
        return ResponseResult.okResult();
    }
}

