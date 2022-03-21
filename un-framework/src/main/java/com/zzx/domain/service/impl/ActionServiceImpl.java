package com.zzx.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Action;
import com.zzx.domain.mapper.ActionMapper;
import com.zzx.domain.service.ActionService;
import com.zzx.domain.vo.action.ActionDetailVo;
import com.zzx.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

/**
 * (Action)表服务实现类
 *
 * @author makejava
 * @since 2022-02-27 19:23:06
 */
@Service("actionService")
public class ActionServiceImpl extends ServiceImpl<ActionMapper, Action> implements ActionService {

    @Override
    public ResponseResult actionDetail(Long id) {
        //查询动作详情
        Action action = getById(id);
        //封装vo
        ActionDetailVo actionDetailVo = BeanCopyUtils.copyBean(action, ActionDetailVo.class);
        return ResponseResult.okResult(actionDetailVo);
    }
}

