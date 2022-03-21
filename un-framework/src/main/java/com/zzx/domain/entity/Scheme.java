package com.zzx.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Scheme)表实体类
 *  定制计划表的实体类
 * @author makejava
 * @since 2022-03-01 14:28:38
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_scheme")
public class Scheme  {
    //主键id
    @TableId
    private Long id;
    //方案名
    private String name;
    //提示信息
    private String outline;
    //二维码
    private String qrCode;
    //状态
    private Integer state;
    //图片
    private String themeImg;
    //标题
    private String title;
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;
    //删除标志（0：未删除，1：已删除）
    private Integer delFlag;



}

