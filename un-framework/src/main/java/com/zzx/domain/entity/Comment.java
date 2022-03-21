package com.zzx.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Comment)表实体类
 *  评论表的实体类
 * @author makejava
 * @since 2022-03-02 11:45:22
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_comment")
public class Comment  {
    //主键id
    @TableId
    private Long id;
    //评论类型（0：代表文章评论，1：代表动态评论）
    private String type;
    //文章id
    private Long articleId;
    //动态id
    private Long communityId;
    //根评论id
    private Long rootId;
    //所回复的目标评论的userId
    private Long toCommentUserId;
    //所回复目标评论的id
    private Long toCommentId;
    //评论内容
    private String content;
    //此评论的userId
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;
    //删除标志（0：未删除，1：已删除）
    private Integer delFlag;



}

