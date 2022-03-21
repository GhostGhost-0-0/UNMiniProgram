package com.zzx.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Community;
import org.springframework.web.multipart.MultipartFile;


/**
 * (Community)表服务接口
 *
 * @author makejava
 * @since 2022-02-28 13:52:52
 */
public interface CommunityService extends IService<Community> {

    ResponseResult hotCommunityList();

    ResponseResult communityList();

    ResponseResult addCommunity(Community community, MultipartFile file);
}

