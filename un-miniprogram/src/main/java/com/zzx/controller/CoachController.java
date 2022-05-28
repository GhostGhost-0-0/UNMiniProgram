package com.zzx.controller;

import com.zzx.annotation.SystemLog;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.service.CoachService;
import com.zzx.domain.service.SchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-03-01 13:44
 **/
@RestController
@RequestMapping("/coach")
public class CoachController {

    @Autowired
    private CoachService coachService;
    @Autowired
    private SchemeService schemeService;

    @GetMapping("/coachList")
    @SystemLog(businessName = "获取私教列表")
    public ResponseResult coachList() {
        return coachService.coachList();
    }

    @GetMapping("/coachDetail")
    @SystemLog(businessName = "获取教练详细介绍")
    public ResponseResult coachDetail(@RequestParam Long id) {
        return coachService.coachDetail(id);
    }

    @GetMapping("/schemeList")
    @SystemLog(businessName = "获取训练方案")
    public ResponseResult schemeList() {
        return schemeService.schemeList();
    }
}
