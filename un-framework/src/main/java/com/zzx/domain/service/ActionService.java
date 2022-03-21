package com.zzx.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Action;


/**
 * (Action)表服务接口
 *
 * @author makejava
 * @since 2022-02-27 19:23:06
 */
public interface ActionService extends IService<Action> {

    ResponseResult actionDetail(Long id);
}

