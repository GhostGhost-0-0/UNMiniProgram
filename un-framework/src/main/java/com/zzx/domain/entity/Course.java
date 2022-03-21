package com.zzx.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Courses)表实体类
 *  训练表的实体类
 * @author makejava
 * @since 2022-02-27 15:06:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_courses")
public class Course {
    //主键，课程的id
    @TableId
    private Long id;

    //课程训练的总动作数
    private Integer classNum;
    //课程的图片
    private String courseCover;
    //是否推荐（1：推荐，0：不推荐）
    private Integer isRecomme;
    //课程的学习人数
    private Integer learnNum;
    //课程的学习难度
    private Integer level;
    //口号
    private String outline;
    //二维码
    private String qrCode;
    //运动类型
    private String sportType;
    //运动类型详情
    private String sportTypeDesc;
    //状态
    private String status;
    //标题
    private String title;
    //顶部图片
    private String topImg;
    //所属训练id
    private Long categoryId;
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;
    //删除标志（0：未删除，1：已删除）
    private Integer delFlag;



}

