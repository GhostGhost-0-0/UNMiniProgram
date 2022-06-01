package com.zzx.domain.service.impl;

import com.zzx.domain.entity.CommunityLike;
import com.zzx.domain.service.RedisService;
import com.zzx.domain.vo.community.LikedCountVo;
import com.zzx.enums.LikedStatusEnum;
import com.zzx.utils.RedisCache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.zzx.constants.SystemConstants.*;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-05-17 15:42
 **/
@Service("RedisService")
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisCache redisCache;

    @Override
    public void saveLike2Redis(Long communityId, String likedUserId, String likedPostId) {
        String key = getLikedKey(communityId, likedUserId, likedPostId);
        redisCache.setCacheMapValue(MAP_KEY_COMMUNITY_LIKED, key, LikedStatusEnum.LIKE.getCode());
        incrementLikedCount(communityId, likedUserId);
    }

    @Override
    public void unlikeFromRedis(Long communityId, String likedUserId, String likedPostId) {
        String key = getLikedKey(communityId, likedUserId, likedPostId);
        redisCache.setCacheMapValue(MAP_KEY_COMMUNITY_LIKED, key, LikedStatusEnum.UNLIKE.getCode());
        decrementLikedCount(communityId, likedUserId);
    }

    @Override
    public void deleteLikedFromRedis(Long communityId, String likedUserId, String likedPostId) {
        String key = getLikedKey(communityId, likedUserId, likedPostId);
        redisCache.delCacheMapValue(MAP_KEY_COMMUNITY_LIKED, key);
    }

    @Override
    public void saveLikedCount2Redis(Map goodNumMap) {
        redisCache.setCacheMap(MAP_KEY_COMMUNITY_LIKED_COUNT,goodNumMap);
    }

    @Override
    public void incrementLikedCount(Long communityId, String likedUserId) {
        String likedCountKey = getLikedCountKey(communityId, likedUserId);
        redisCache.incrementCacheMapValue(MAP_KEY_COMMUNITY_LIKED_COUNT, likedCountKey, COMMUNITY_LIKED_COUNT_INCREMENT.longValue());
    }

    @Override
    public void decrementLikedCount(Long communityId, String likedUserId) {
        String likedCountKey = getLikedCountKey(communityId, likedUserId);
        redisCache.incrementCacheMapValue(MAP_KEY_COMMUNITY_LIKED_COUNT, likedCountKey, COMMUNITY_LIKED_COUNT_DECREMENT.longValue());
    }

    @Override
    public CommunityLike getOneLikedDataFromRedis(Long communityId, String likedUserId, String likedPostId) {
        List<CommunityLike> communityLikes = getLikedDataFromRedis();
        for (CommunityLike communityLike : communityLikes) {
            if (Long.valueOf(communityId).equals(communityLike.getCommunityId())
                    && likedUserId.equals(communityLike.getLikedUserId())
                    && likedPostId.equals(communityLike.getLikedPostId())) {
                return communityLike;
            }
        }
        return null;
    }

    @Override
    public List<CommunityLike> getLikedDataFromRedis() {
        Map<String, Object> userLikedMap = redisCache.getCacheMap(MAP_KEY_COMMUNITY_LIKED);
        List<CommunityLike> userLikes = userLikedMap.entrySet()
                .stream()
                .map(entry -> {
                    String key = entry.getKey();
                    String[] split = key.split("::");
                    Long communityId = Long.valueOf(split[0]);
                    String likedUserId = split[1];
                    String likedPostId = split[2];
                    Integer status = (Integer) entry.getValue();
                    return new CommunityLike(communityId, likedUserId, likedPostId, status);
                }).collect(Collectors.toList());
        //redisCache.deleteObject(MAP_KEY_USER_LIKED);
        return userLikes;
    }

    @Override
    public List<LikedCountVo> getLikedCountFromRedis() {
        Map<String, Object> userLikeCountMap = redisCache.getCacheMap(MAP_KEY_COMMUNITY_LIKED_COUNT);
        List<LikedCountVo> likedCountVos = userLikeCountMap.entrySet()
                .stream()
                .map(entry -> {
                    String key = entry.getKey();
                    String[] split = key.split("::");
                    Long communityId = Long.valueOf(split[0]);
                    String likedUserId = split[1];
                    Integer goodNum = (Integer) entry.getValue();
                    return new LikedCountVo(communityId, likedUserId, goodNum);
                }).collect(Collectors.toList());
        //redisCache.deleteObject(MAP_KEY_USER_LIKED_COUNT);
        return likedCountVos;
    }

    /**
     * 生成 Redis 的 Map 键，以"::"分割，示例 "2222::3333::6666"，用来存储点赞对象
     * @param communityId 动态id
     * @param likedUserId 被点赞人id
     * @param likedPostId 点赞人id
     * @return 生成的键
     */
    public String getLikedKey(Long communityId, String likedUserId, String likedPostId) {
        StringBuilder builder = new StringBuilder();
        builder.append(communityId)
                .append("::")
                .append(likedUserId)
                .append("::")
                .append(likedPostId);
        return builder.toString();
    }

    /**
     * 生成 Redis 的 Map 键，以"::"分割，示例 "2222::3333",用来存储点赞数量
     * @param communityId 动态id
     * @param likedUserId 被点赞人id
     * @return 生成的键
     */
    public String getLikedCountKey(Long communityId, String likedUserId) {
        StringBuilder builder = new StringBuilder();
        builder.append(communityId)
                .append("::")
                .append(likedUserId);
        return builder.toString();
    }
}
