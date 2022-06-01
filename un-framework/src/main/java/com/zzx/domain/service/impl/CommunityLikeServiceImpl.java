package com.zzx.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Community;
import com.zzx.domain.entity.CommunityLike;
import com.zzx.domain.mapper.CommunityLikeMapper;
import com.zzx.domain.service.CommunityService;
import com.zzx.domain.service.RedisService;
import com.zzx.domain.service.CommunityLikeService;
import com.zzx.domain.vo.PageVo;
import com.zzx.domain.vo.community.LikedCountVo;
import com.zzx.enums.AppHttpCodeEnum;
import com.zzx.enums.LikedStatusEnum;
import com.zzx.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-05-17 15:00
 **/
@Service("UserLikeService")
public class CommunityLikeServiceImpl extends ServiceImpl<CommunityLikeMapper, CommunityLike> implements CommunityLikeService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private CommunityService communityService;

    @Override
    @Transactional
    public Boolean saveCommunityLike(CommunityLike communityLike) {
        return save(communityLike);
    }

    @Override
    public List<CommunityLike> saveAll(List<CommunityLike> communityLikes) {
        return saveAll(communityLikes);
    }

    @Override
    public ResponseResult getLikedListByLikedUserId(String likedUserId) {
        //查询条件
        LambdaQueryWrapper<CommunityLike> wrapper = new LambdaQueryWrapper<>();
        //查询被点赞人的 id ，以及点赞状态为 1
        wrapper.eq(CommunityLike::getLikedUserId,likedUserId);
        wrapper.eq(CommunityLike::getStatus, LikedStatusEnum.LIKE);
        //进行分页
        Page<CommunityLike> page = new Page<>(1,5);
        List<CommunityLike> userLikes = page(page, wrapper).getRecords();
        return ResponseResult.okResult(new PageVo(userLikes,page.getTotal()));
    }

    @Override
    public ResponseResult getLikedListByLikedPostId(String likedPostId) {
        //查询条件
        LambdaQueryWrapper<CommunityLike> wrapper = new LambdaQueryWrapper<>();
        //查询点赞人的 id ，以及点赞状态为 1
        wrapper.eq(CommunityLike::getLikedUserId,likedPostId);
        wrapper.eq(CommunityLike::getStatus, LikedStatusEnum.LIKE);
        //进行分页
        Page<CommunityLike> page = new Page<>(1,5);
        List<CommunityLike> userLikes = page(page, wrapper).getRecords();
        return ResponseResult.okResult(new PageVo(userLikes,page.getTotal()));
    }

    @Override
    public CommunityLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId) {
        //查询条件
        LambdaQueryWrapper<CommunityLike> wrapper = new LambdaQueryWrapper<>();
        //查询点赞人的 id ，以及点赞状态为 1
        wrapper.eq(CommunityLike::getLikedUserId,likedUserId);
        wrapper.eq(CommunityLike::getLikedPostId,likedPostId);
        wrapper.eq(CommunityLike::getStatus, LikedStatusEnum.LIKE);
        //进行分页
        return getOne(wrapper);
    }

    @Override
    public void transLikedFromRedis2DB() {
        //从 Redis 中获取点赞对象数据
        List<CommunityLike> likedDataFromRedis = redisService.getLikedDataFromRedis();
        LambdaUpdateWrapper<CommunityLike> updateWrapper = new LambdaUpdateWrapper<>();
        for (CommunityLike communityLike : likedDataFromRedis) {
            updateWrapper
                    .eq(CommunityLike::getCommunityId, communityLike.getCommunityId())
                    .eq(CommunityLike::getLikedUserId, communityLike.getLikedUserId())
                    .eq(CommunityLike::getLikedPostId, communityLike.getLikedPostId())
                    .set(CommunityLike::getStatus, communityLike.getStatus())
                    .set(CommunityLike::getUpdateTime, new Date());
            update(updateWrapper);
            updateWrapper.clear();
        }
    }

    @Override
    public void transLikedCountFromRedis2DB() {
        List<LikedCountVo> likedCountFromRedis = redisService.getLikedCountFromRedis();
        for (LikedCountVo likedCountVo : likedCountFromRedis) {
            Community community = communityService.getById(likedCountVo.getCommunityId());
            try {
                if (community != null) {
                    Integer goodNum = likedCountVo.getGoodNum();
                    LambdaUpdateWrapper<Community> wrapper = new LambdaUpdateWrapper<>();
                    wrapper.eq(Community::getId,community.getId()).set(Community::getGoodNum,goodNum);
                    communityService.update(null,wrapper);
                }
            } catch (Exception e) {
                throw new SystemException(AppHttpCodeEnum.COMMUNITY_NULL_ERROR);
            }
        }
    }
}
