package com.zzx.controller;

import com.zzx.domain.ResponseResult;
import com.zzx.domain.service.UpLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-03-02 19:17
 **/
@RestController
public class upLoadController {

    @Autowired
    UpLoadService upLoadService;

    @PostMapping("/upload")
    private ResponseResult upLoadImg(@RequestBody MultipartFile file) {
        return upLoadService.upLoadOss(file);
    }
}
