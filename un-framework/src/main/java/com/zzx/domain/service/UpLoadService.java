package com.zzx.domain.service;

import com.zzx.config.CloudStorageConfig;
import com.zzx.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-03-02 18:53
 **/
public abstract class UpLoadService {

    protected CloudStorageConfig config;

    public abstract ResponseResult upLoadOss(MultipartFile file);
}
