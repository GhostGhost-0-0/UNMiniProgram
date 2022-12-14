package com.zzx.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zzx.annotation.SystemLog;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Comment;
import com.zzx.domain.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-03-02 11:47
 **/
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    @SystemLog(businessName = "获取评论列表")
    public ResponseResult commentList(@RequestParam Long articleId,
                                      @RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize) {
        return commentService.commentList(articleId, pageNum, pageSize);
    }

    @PostMapping("/comment")
    @SystemLog(businessName = "发表评论")
    public ResponseResult comment(@RequestBody Comment comment) {
        return commentService.comment(comment);
    }
}
