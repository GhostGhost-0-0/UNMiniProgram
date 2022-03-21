package com.zzx.domain.vo.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-03-02 11:59
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {
    //主键id
    private Long id;
    //文章id
    private Long articleId;
    //根评论id
    private Long rootId;
    //所回复的目标评论的userId
    private Long toCommentUserId;
    //所回复的目标的用户昵称
    private String toCommentUserName;
    //所回复目标评论的id
    private Long toCommentId;
    //评论内容
    private String content;
    //此评论的userId
    private Long createBy;
    //此评论的用户昵称
    private String createName;
    //此评论的用户头像
    private String createAvatar;

    private Date createTime;
    //子评论
    private List<CommentVo> children;


}
