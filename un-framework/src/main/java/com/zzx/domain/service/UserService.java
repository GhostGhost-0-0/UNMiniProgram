package com.zzx.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.User;


/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2022-02-28 13:48:11
 */
public interface UserService extends IService<User> {

    ResponseResult getOpenId(String code);

    ResponseResult login(String openid, String avatar, String nickName, String sex, String city, String province, String country);
}

