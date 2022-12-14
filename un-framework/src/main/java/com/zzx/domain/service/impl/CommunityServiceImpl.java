package com.zzx.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.constants.SystemConstants;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Community;
import com.zzx.domain.entity.CommunityLike;
import com.zzx.domain.entity.User;
import com.zzx.domain.mapper.CommunityMapper;
import com.zzx.domain.service.*;
import com.zzx.domain.vo.community.CommunityDetailVo;
import com.zzx.domain.vo.community.CommunityVo;
import com.zzx.domain.vo.PageVo;
import com.zzx.domain.vo.community.LikedCountVo;
import com.zzx.enums.AppHttpCodeEnum;
import com.zzx.enums.LikedStatusEnum;
import com.zzx.exception.SystemException;
import com.zzx.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * (Community)表服务实现类
 *
 * @author makejava
 * @since 2022-02-28 13:52:52
 */
@Service("communityService")
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, Community> implements CommunityService {

    @Autowired
    private UserService userService;
    @Autowired
    private UpLoadService upLoadService;
    @Autowired
    private CommunityLikeService communityLikeService;
    @Autowired
    private RedisService redisService;

    @Override
    public ResponseResult hotCommunityList() {
        //查询热门动态，只要点赞数大于0的
        LambdaQueryWrapper<Community> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.gt(Community::getGoodNum, SystemConstants.COMMUNITY_GOOD_NUM);
        queryWrapper.orderByDesc(Community::getGoodNum);
        List<Community> communityList = list(queryWrapper);
        //从 redis 中获取动态的点赞数量
        for (Community community : communityList) {
            community.setGoodNum(getGoodNumFromRedis(community));
        }
        //封装vo
        List<CommunityVo> hotCommunityVos = toCommunityVo(communityList);

        return ResponseResult.okResult(hotCommunityVos);
    }

    @Override
    public ResponseResult communityList() {
        //查询全部动态，并按照时间排序
        LambdaQueryWrapper<Community> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Community::getCreateTime);
        //分页，一页最多显示五条
        Page<Community> page = new Page<>(1,5);
        List<Community> communityList = page(page, queryWrapper).getRecords();
        //从 redis 中获取动态的点赞数量
        for (Community community : communityList) {
            community.setGoodNum(getGoodNumFromRedis(community));
        }
        //封装vo
        List<CommunityVo> communityVos = toCommunityVo(communityList);
        return ResponseResult.okResult(new PageVo(communityVos, page.getTotal()));
    }

    @Override
    public ResponseResult addCommunity(Community community, MultipartFile file) {
        if (!StringUtils.hasText(community.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NULL_ERROR);
        }
        String path = upLoadService.upLoadOss(file);
        community.setPicture(path);
        save(community);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult communityDetail(Long id) {
        //根据 id 查询对应的动态信息
        Community community = getById(id);
        //从 redis 中获取动态的点赞数量
        Integer goodNum = getGoodNumFromRedis(community);
        community.setGoodNum(goodNum);
        //根据动态的作者 id 查询作者信息
        User user = userService.getById(community.getOpenid());
        //封装 vo
        CommunityDetailVo communityDetailVo = BeanCopyUtils.copyBean(community, CommunityDetailVo.class);
        communityDetailVo.setAuthorName(user.getNickName());
        communityDetailVo.setAuthorAvatar(user.getAvatar());
        return ResponseResult.okResult(communityDetailVo);
    }

    @Override
    public ResponseResult communityLike(Long communityId, String likedUserId, String likedPostId) {
        CommunityLike communityLike = redisService.getOneLikedDataFromRedis(communityId, likedUserId, likedPostId);
        if (communityLike == null) {
            communityLikeService.saveCommunityLike(new CommunityLike(communityId,likedUserId,likedPostId, LikedStatusEnum.LIKE.getCode()));
            redisService.saveLike2Redis(communityId,likedUserId,likedPostId);
            return ResponseResult.okResult(200,"操作成功！");
        }
        switch (communityLike.getStatus()) {
            case 1 :
                redisService.unlikeFromRedis(communityId,likedUserId,likedPostId);
                break;
            case 0 :
                redisService.saveLike2Redis(communityId, likedUserId, likedPostId);
                break;
        }
        return ResponseResult.okResult(200,"操作成功！");
    }

    private List<CommunityVo> toCommunityVo(List<Community> communityList) {
        List<CommunityVo> CommunityVos = BeanCopyUtils.copyBeanList(communityList, CommunityVo.class);
        Set<String> ids = new LinkedHashSet<>();
        communityList.stream().map(community -> ids.add(community.getOpenid())).collect(Collectors.toSet());
        List<User> authorList = userService.listByIds(ids);
        CommunityVos.stream()
                .map(CommunityVo -> {
                    for (User user : authorList) {
                        CommunityVo.setAuthorName(user.getNickName());
                        CommunityVo.setAuthorAvatar(user.getAvatar());
                    }
                    return CommunityVo;
                }).collect(Collectors.toList());
        return CommunityVos;
    }

    private Integer getGoodNumFromRedis(Community community) {
        List<LikedCountVo> likedCountVos = redisService.getLikedCountFromRedis();
        for (LikedCountVo likedCountVo : likedCountVos) {
            if (likedCountVo.getCommunityId() == community.getId() && likedCountVo.getLikedUserId() == community.getOpenid()) {
                return likedCountVo.getGoodNum();
            }
        }
        return null;
    }
}

