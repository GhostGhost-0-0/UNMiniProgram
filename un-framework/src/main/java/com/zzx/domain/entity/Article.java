package com.zzx.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Article)表实体类
 *  文章表的实体类
 * @author makejava
 * @since 2022-03-01 19:14:35
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_article")
public class Article  {
    //主键id
    @TableId
    private Long id;
    //标题
    private String title;
    //文章内容
    private String content;
    //文章摘要
    private String outline;
    //所属分类id
    private Long categoryId;
    //封面
    private String cover;
    //阅读数
    private Long reader;
    //状态
    private Integer state;
    //作者id
    private Long createBy;
    //创建时间
    private Date createTime;
    //更新人id
    private Long updateBy;
    //更新时间
    private Date updateTime;
    //删除标志（0：未删除，1：已删除）
    private Integer delFlag;



}

