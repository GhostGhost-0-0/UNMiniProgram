package com.zzx.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Shop)表实体类
 *
 * @author makejava
 * @since 2022-03-04 14:49:14
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_shop")
public class Shop  {
    //主键id
    @TableId
    private Long id;

    private String advantage;
    
    private String content;
    
    private String efficacy;
    //使用方法
    private String instructions;
    
    private String longPicture;
    //商品名称
    private String name;
    
    private String outline;
    //适用人群
    private String people;
    //口味
    private String taste;
    
    private String themeImg;
    //价格
    private Float price;
    //状态
    private Integer state;
    //二维码
    private String qrCode;
    //商品类型
    private String type;
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;
    
    private Integer delFlag;



}

