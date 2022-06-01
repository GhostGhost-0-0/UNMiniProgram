package com.zzx.domain.service;

import com.zzx.domain.entity.CommunityLike;
import com.zzx.domain.vo.community.LikedCountVo;

import java.util.List;
import java.util.Map;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-05-17 15:25
 **/
public interface RedisService {

    /**
     * 点赞。状态为 1
     * @param communityId 动态id
     * @param likedUserId 被点赞人id
     * @param likedPostId 点赞人id
     */
    void saveLike2Redis(Long communityId, String likedUserId, String likedPostId);

    /**
     * 取消点赞。状态为 0
     * @param communityId 动态id
     * @param likedUserId 被点赞人id
     * @param likedPostId 点赞人id
     */
    void unlikeFromRedis(Long communityId, String likedUserId, String likedPostId);

    /**
     * 从 Redis 中删除一条动态点赞数据
     * @param communityId 动态id
     * @param likedUserId 被点赞人id
     * @param likedPostId 点赞人id
     */
    void deleteLikedFromRedis(Long communityId, String likedUserId, String likedPostId);

    /**
     * 把动态的点赞数量存入到 redis 中
     * @param goodNumMap 带有动态id和作者id的点赞数量 Map
     */
    void saveLikedCount2Redis(Map goodNumMap);

    /**
     * 该条动态的点赞数加一
     * @param communityId 动态id
     */
    void incrementLikedCount(Long communityId, String likedUserId);

    /**
     * 该条动态的点赞数减一
     * @param communityId 动态id
     */
    void decrementLikedCount(Long communityId, String likedUserId);

    /**
     * 获取 Redis 中单条点赞数据
     * @param communityId 动态id
     * @param likedUserId 被点赞人id
     * @param likedPostId 点赞人id
     */
    CommunityLike getOneLikedDataFromRedis(Long communityId, String likedUserId, String likedPostId);

    /**
     * 获取 Redis 中存储的所有点赞数据
     * @return 返回列表
     */
    List<CommunityLike> getLikedDataFromRedis();

    /**
     * 获取 Redis 中存储的所有点赞数量
     * @return 返回列表
     */
    List<LikedCountVo> getLikedCountFromRedis();
}
