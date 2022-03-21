package com.zzx.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Shop;
import com.zzx.domain.mapper.ShopMapper;
import com.zzx.domain.service.ShopService;
import com.zzx.domain.vo.PageVo;
import com.zzx.domain.vo.shop.ShopDetailVo;
import com.zzx.domain.vo.shop.ShopVo;
import com.zzx.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Shop)表服务实现类
 *
 * @author makejava
 * @since 2022-03-04 14:49:14
 */
@Service("shopService")
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Override
    public ResponseResult getHotAndNew() {
        //查询全部的商品
        //分页查询
        Page<Shop> page = new Page<>(1,40);
        List<Shop> shopList = page(page).getRecords();
        //封装vo
        List<ShopVo> shopVos = BeanCopyUtils.copyBeanList(shopList, ShopVo.class);
        return ResponseResult.okResult(new PageVo(shopVos, page.getTotal()));
    }

    @Override
    public ResponseResult getShopDetail(Long id) {
        //根据id查询
        Shop shop = getById(id);
        //封装vo
        ShopDetailVo shopDetailVo = BeanCopyUtils.copyBean(shop, ShopDetailVo.class);
        return ResponseResult.okResult(shopDetailVo);
    }
}

