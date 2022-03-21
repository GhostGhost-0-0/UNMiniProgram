package com.zzx.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Coach;


/**
 * (Coach)表服务接口
 *
 * @author makejava
 * @since 2022-03-01 13:43:40
 */
public interface CoachService extends IService<Coach> {

    ResponseResult coachList();

    ResponseResult coachDetail(Long id);
}

