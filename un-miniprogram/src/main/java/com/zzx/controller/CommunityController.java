package com.zzx.controller;

import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Community;
import com.zzx.domain.service.CommunityService;
import com.zzx.enums.AppHttpCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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
    public ResponseResult hotCommunityList() {
        return communityService.hotCommunityList();
    }

    @GetMapping("/communityList")
    public ResponseResult communityList() {
        return communityService.communityList();
    }

    @PostMapping("/addCommunity")
    public ResponseResult addCommunity(@RequestBody Community community, @RequestBody MultipartFile file) {
        return communityService.addCommunity(community,file);
    }
}
