package com.zzx.job;

import com.zzx.domain.service.CommunityLikeService;
import com.zzx.domain.service.CommunityService;
import com.zzx.domain.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class communityLikeJob {

    @Autowired
    private CommunityLikeService communityLikeService;

    @Scheduled(cron = "* * 0/2 * * ?")
    public void updateGoodNumAndCommunityLike2DBJob() {
        communityLikeService.transLikedCountFromRedis2DB();
        communityLikeService.transLikedFromRedis2DB();
    }
}
