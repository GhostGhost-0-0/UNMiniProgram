package com.zzx.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.constants.SystemConstants;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Comment;
import com.zzx.domain.entity.User;
import com.zzx.domain.mapper.CommentMapper;
import com.zzx.domain.service.CommentService;
import com.zzx.domain.service.UserService;
import com.zzx.domain.vo.PageVo;
import com.zzx.domain.vo.comment.CommentVo;
import com.zzx.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * (Comment)表服务实现类
 *
 * @author makejava
 * @since 2022-03-02 11:45:22
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        //根据文章id查询
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //对 articleId 进行判断
        queryWrapper.eq(Comment::getArticleId, articleId);
        //根评论id为-1
        queryWrapper.eq(Comment::getRootId, SystemConstants.COMMENT_ROOT_ID);
        //分页查询
        Page<Comment> page = new Page<>(pageNum,pageSize);
        List<Comment> commentList = page(page, queryWrapper).getRecords();
        //封装vo
        List<CommentVo> commentVos = toCommentVo(commentList);
        //查询所有根评论对应的子评论集合
        commentVos.stream()
                .map(commentVo -> {
                    commentVo.setChildren(getChildren(commentVo.getId()));
                    return commentVo;
                })
                .collect(Collectors.toList());
        return ResponseResult.okResult(new PageVo(commentVos, page.getTotal()));
    }

    @Override
    public ResponseResult comment(Comment comment) {
        //
        return null;
    }

    private List<CommentVo> getChildren(Long rootId) {
        //根据rootId查询子评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,rootId);
        //根据创建时间排序
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> commentList = list(queryWrapper);
        //封装vo
        List<CommentVo> commentVos = toCommentVo(commentList);
        return commentVos;
    }

    private List<CommentVo> toCommentVo(List<Comment> commentList) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(commentList, CommentVo.class);
        //id集合，用来存放用户id
        Set<Long> ids = new HashSet<>();
        commentList.stream()
                .map(comment -> ids.add(comment.getCreateBy())).collect(Collectors.toList());
        Map<Long, User> userMap = new HashMap<>();
        //根据id集合查询出用户列表
        List<User> userList = userService.listByIds(ids);
        //将对应的id与user对象存放到Map中
        userList.stream()
                .map(user -> userMap.put(user.getId(), user)).collect(Collectors.toList());
        commentVos.stream()
                .map(commentVo -> {
                    //根据Map查询出对应的信息，设置评论人的昵称和头像
                    commentVo.setCreateName(userMap.get(commentVo.getCreateBy()).getNickName());
                    commentVo.setCreateAvatar(userMap.get(commentVo.getCreateBy()).getAvatar());
                    if (commentVo.getToCommentUserId() != -1) {
                        //若 getToCommentUserId ！= -1 表示这是这是一条回复他人的评论，需要显示被回复人的呢称
                        commentVo.setToCommentUserName(userMap.get(commentVo.getToCommentUserId()).getNickName());
                    }
                    return commentVo;
                }).collect(Collectors.toList());
        return commentVos;
    }
}

