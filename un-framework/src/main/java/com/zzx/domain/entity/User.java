package com.zzx.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (User)表实体类
 *  用户表的实体类
 * @author makejava
 * @since 2022-02-28 13:48:11
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class User  {
    //主键
    @TableId
    private Long id;
    //昵称
    private String nickName;
    //微信号
    private String wechatNumber;
    //头像
    private String avatar;
    //性别（0：女，1：男）
    private String sex;
    //国家
    private String country;
    //省份
    private String province;
    //城市
    private String city;
    //小程序用户的openid
    private String openid;
    //创建人的用户id
    private Long createBy;
    //创建时间
    private Date createTime;
    //更新人
    private Long updateBy;
    //更新时间
    private Date updateTime;
    //删除标志（0：未删除，1：已删除）
    private Integer delFlag;



}

