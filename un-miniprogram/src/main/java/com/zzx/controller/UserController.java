package com.zzx.controller;

import com.zzx.annotation.SystemLog;
import com.zzx.config.WxConfig;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.User;
import com.zzx.domain.service.UserService;
import com.zzx.domain.vo.user.LoginVo;
import com.zzx.domain.vo.user.UserInfoVo;
import com.zzx.utils.BeanCopyUtils;
import com.zzx.utils.JwtUtil;
import com.zzx.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-03-03 14:57
 **/
@RestController
@RequestMapping("/me")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisCache redisCache;

    @GetMapping("/getOpenId")
    @SystemLog(businessName = "获取openId")
    public ResponseResult getOpenId(String code) {
        return userService.getOpenId(code);
    }

    @PostMapping("/login")
    @SystemLog(businessName = "登录")
    public ResponseResult login(@RequestBody User insertUser) {
        ResponseResult result = new ResponseResult();
        User user = userService.login(insertUser);
        if (user == null) {
           result.error(0, "登陆失败");
        }else {
            String openid = user.getOpenid();
            String jwt = JwtUtil.createJWT(openid);
            //把用户信息存入 Redis
            redisCache.setCacheObject("WeChatLogin:" + openid, user);
            //把 token 和 userInfo 封装返回
            //把 User 转换成 UserInfo
            UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
            LoginVo loginVo = new LoginVo(jwt, userInfoVo);
            result.ok(loginVo);
        }
        return result;
    }
}
