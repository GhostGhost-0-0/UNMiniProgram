package com.zzx.controller;

import com.zzx.annotation.SystemLog;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-03-04 14:49
 **/
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/getHotAndNew")
    @SystemLog(businessName = "获取热门商品")
    public ResponseResult getHotAndNew() {
        return shopService.getHotAndNew();
    }

    @GetMapping("/getShopDetail")
    @SystemLog(businessName = "获取商品详情")
    public ResponseResult getShopDetail(@RequestParam Long id) {
        return shopService.getShopDetail(id);
    }
}
