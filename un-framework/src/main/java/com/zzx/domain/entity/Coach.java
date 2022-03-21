package com.zzx.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Coach)表实体类
 *  教练表的实体类
 * @author makejava
 * @since 2022-03-01 13:43:40
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_coach")
public class Coach  {
    //主键id
    @TableId
    private Long id;

    //教练名称
    private String name;
    //性别
    private String gender;
    //头像
    private String headImg;
    //顶部头像
    private String topImg;
    //状态
    private Integer state;
    //身份
    private String type;
    //地点
    private String address;
    //自我介绍
    private String content;
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;
    //删除标志（0：未删除，1：已删除）
    private Integer delFlag;



}

