package com.zzx.constants;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-02-27 17:40
 **/
public class SystemConstants {

    //动态的点赞数为0
    public static final int COMMUNITY_GOOD_NUM = 0;
    //根评论 rootId 为-1
    public static final int COMMENT_ROOT_ID = -1;
    //redis键，用来存储动态点赞对象
    public static final String MAP_KEY_COMMUNITY_LIKED = "MAP_COMMUNITY_LIKED";
    //redis键，用来存储动态点赞数
    public static final String MAP_KEY_COMMUNITY_LIKED_COUNT = "MAP_COMMUNITY_LIKED_COUNT";
    //redis 自增
    public static final Integer COMMUNITY_LIKED_COUNT_INCREMENT = 1;
    //redis 自减
    public static final Integer COMMUNITY_LIKED_COUNT_DECREMENT = -1;
}
