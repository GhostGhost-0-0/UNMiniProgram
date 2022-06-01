package com.zzx.domain.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.beans.factory.annotation.Value;

/**
 * (Community)表实体类
 *  动态表的实体类
 * @author makejava
 * @since 2022-02-28 13:52:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_community")
public class Community  {
    //打卡动态id
    @TableId
    private Long id;

    //健身心得
    private String content;
    //健身地点
    private String address;
    //上传的图片
    private String picture;
    //点赞数
    private Integer goodNum;
    //openid
    private String openid;
    //作者，用户id
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    //打卡时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE )
    private Long updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //删除标志（0：未删除，1：已删除）
    private Integer delFlag;

}

