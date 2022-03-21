package com.zzx.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Shop;


/**
 * (Shop)表服务接口
 *
 * @author makejava
 * @since 2022-03-04 14:49:14
 */
public interface ShopService extends IService<Shop> {

    ResponseResult getHotAndNew();

    ResponseResult getShopDetail(Long id);
}

