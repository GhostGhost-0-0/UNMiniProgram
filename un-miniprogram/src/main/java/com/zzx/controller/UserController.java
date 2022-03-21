package com.zzx.controller;

import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.User;
import com.zzx.domain.service.UserService;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.BooleanString;
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

    @GetMapping("/getOpenId")
    public ResponseResult getOpenId(String code) {
        return userService.getOpenId(code);
    }

    @GetMapping("/login")
    public ResponseResult login(@RequestParam("openid") String openid,
                                @RequestParam("avatarUrl") String avatar,
                                @RequestParam("nickName") String nickName,
                                @RequestParam("gender") String sex,
                                @RequestParam("city") String city,
                                @RequestParam("province") String province,
                                @RequestParam("country") String country) {
        return userService.login(openid,avatar,nickName,sex,city,province,country);
    }
}
