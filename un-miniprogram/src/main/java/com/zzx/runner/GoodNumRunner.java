package com.zzx.runner;

import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Community;
import com.zzx.domain.mapper.CommunityMapper;
import com.zzx.domain.service.CommunityService;
import com.zzx.domain.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GoodNumRunner implements CommandLineRunner {

    @Resource
    private CommunityMapper communityMapper;
    
    @Autowired
    private RedisService redisService;

    @Override
    public void run(String... args) throws Exception {
        //查询动态信息
        List<Community> communityList = communityMapper.selectList(null);
        //把动态的点赞数量存储到 redis 中
        Map<String, Integer> goodNumMap = communityList.stream()
                .collect(Collectors.toMap(community -> community.getId().toString()+"::"+community.getOpenid(), community -> community.getGoodNum()));
        redisService.saveLikedCount2Redis(goodNumMap);
    }
}
