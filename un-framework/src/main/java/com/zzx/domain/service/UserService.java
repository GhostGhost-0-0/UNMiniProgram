package com.zzx.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.User;
import com.zzx.domain.entity.WeChatLoginCode;


/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2022-02-28 13:48:11
 */
public interface UserService extends IService<User> {

    ResponseResult getOpenId(String code);

    User login(User insertUser);
}

