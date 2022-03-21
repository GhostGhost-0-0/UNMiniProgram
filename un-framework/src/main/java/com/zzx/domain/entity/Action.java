package com.zzx.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Action)表实体类
 *  动作表的实体类
 * @author makejava
 * @since 2022-02-27 19:23:06
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_action")
public class Action  {
    //主键，动作id
    @TableId
    private Long id;

    //第几个动作
    private Integer classNo;
    //课程id
    private Long courseId;
    //描述
    private String description;
    //封面
    private String detailsCover;
    //收藏
    private Integer star;
    //状态
    private String state;
    //标题
    private String title;
    //配套教学视频
    private String video;
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;
    //删除标志（0：未删除，1：已删除）
    private Integer delFlag;



}

