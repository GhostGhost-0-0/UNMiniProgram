package com.zzx.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Scheme;
import com.zzx.domain.mapper.SchemeMapper;
import com.zzx.domain.service.SchemeService;
import com.zzx.domain.vo.scheme.SchemeVo;
import com.zzx.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Scheme)表服务实现类
 *
 * @author makejava
 * @since 2022-03-01 14:28:41
 */
@Service("schemeService")
public class SchemeServiceImpl extends ServiceImpl<SchemeMapper, Scheme> implements SchemeService {

    @Override
    public ResponseResult schemeList() {
        //查询全部的计划
        List<Scheme> schemeList = list();
        //封装vo
        List<SchemeVo> schemeVos = BeanCopyUtils.copyBeanList(schemeList, SchemeVo.class);
        return ResponseResult.okResult(schemeVos);
    }
}

