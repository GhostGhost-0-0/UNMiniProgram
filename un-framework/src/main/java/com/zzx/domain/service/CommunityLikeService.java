package com.zzx.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.CommunityLike;

import java.util.List;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-05-17 14:59
 **/
public interface CommunityLikeService extends IService<CommunityLike> {

    /**
     * 保存点赞记录
     * @param communityLike
     * @return
     */
    Boolean saveCommunityLike(CommunityLike communityLike);

    /**
     * 批量保存或修改
     * @param communityLikes
     * @return
     */
    List<CommunityLike> saveAll(List<CommunityLike> communityLikes);

    /**
     * 根据被点赞人的id查询点赞列表（即查询都谁给这个人点赞过）
     * @param likedUserId
     * @return
     */
    ResponseResult getLikedListByLikedUserId(String likedUserId);

    /**
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     * @param likedPostId
     * @return
     */
    ResponseResult getLikedListByLikedPostId(String likedPostId);

    /**
     * 通过被点赞人和点赞人 id 查询是否存在点赞记录
     * @param likedUserId
     * @param likedPostId
     * @return
     */
    CommunityLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId);

    /**
     * 将 Redis 里的点赞数据存入数据库中
     */
    void transLikedFromRedis2DB();

    /**
     * 将 Redis 中的点赞数量数据存入数据库
     */
    void transLikedCountFromRedis2DB();
}
