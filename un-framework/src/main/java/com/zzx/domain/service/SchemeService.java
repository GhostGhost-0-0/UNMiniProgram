package com.zzx.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Scheme;


/**
 * (Scheme)表服务接口
 *
 * @author makejava
 * @since 2022-03-01 14:28:38
 */
public interface SchemeService extends IService<Scheme> {

    ResponseResult schemeList();
}

