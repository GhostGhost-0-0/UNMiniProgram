package com.zzx.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Coach;
import com.zzx.domain.mapper.CoachMapper;
import com.zzx.domain.service.CoachService;
import com.zzx.domain.vo.coach.coachDetailVo;
import com.zzx.domain.vo.coach.coachVo;
import com.zzx.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Coach)表服务实现类
 *
 * @author makejava
 * @since 2022-03-01 13:43:40
 */
@Service("coachService")
public class CoachServiceImpl extends ServiceImpl<CoachMapper, Coach> implements CoachService {

    @Override
    public ResponseResult coachList() {
        //查询全部的教练信息
        List<Coach> coachList = list();
        //封装vo
        List<coachVo> coachVoList = BeanCopyUtils.copyBeanList(coachList, coachVo.class);
        return ResponseResult.okResult(coachVoList);
    }

    @Override
    public ResponseResult coachDetail(Long id) {
        //根据id查询对应教练的详细信息
        Coach coach = getById(id);
        //封装vo
        coachDetailVo coachDetailVo = BeanCopyUtils.copyBean(coach, coachDetailVo.class);
        return ResponseResult.okResult(coachDetailVo);
    }
}

