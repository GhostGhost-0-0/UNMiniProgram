package com.zzx.controller;

import com.zzx.domain.ResponseResult;
import com.zzx.domain.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-02-27 19:51
 **/
@RestController
@RequestMapping("/action")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @GetMapping("/actionDetail")
    public ResponseResult actionDetail(@RequestParam("actionId") Long id) {
        return actionService.actionDetail(id);
    }
}
