package com.zzx.controller;

import com.alibaba.fastjson.annotation.JSONField;
import com.zzx.annotation.SystemLog;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Community;
import com.zzx.domain.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-02-28 13:53
 **/
@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @GetMapping("/hotCommunityList")
    @SystemLog(businessName = "获取热门社区动态列表")
    public ResponseResult hotCommunityList() {
        return communityService.hotCommunityList();
    }

    @GetMapping("/communityList")
    @SystemLog(businessName = "获取全部社区动态列表")
    public ResponseResult communityList() {
        return communityService.communityList();
    }

    @GetMapping("/communityDetail/{id}")
    @SystemLog(businessName = "获取动态详情")
    public ResponseResult communityDetail(@PathVariable("id") Long id) {
        return communityService.communityDetail(id);
    }

    @PostMapping("/addCommunity")
    //@SystemLog(businessName = "发布社区动态")
    public ResponseResult addCommunity(@RequestParam(value = "content") String content, @RequestParam(value = "address") String address,
                                       @RequestParam(value = "openid") String openid,
                                       @RequestParam(value = "file") MultipartFile file) {
        Community community = new Community();
        community.setContent(content);
        community.setAddress(address);
        community.setOpenid(openid);
        return communityService.addCommunity(community,file);
    }
}
