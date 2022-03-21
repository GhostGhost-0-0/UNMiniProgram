package com.zzx.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Comment;


/**
 * (Comment)表服务接口
 *
 * @author makejava
 * @since 2022-03-02 11:45:22
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult comment(Comment comment);
}

