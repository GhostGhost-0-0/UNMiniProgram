package com.zzx.domain.vo.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-03-04 15:22
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopDetailVo {
    //主键id
    private Long id;
    //优势特点
    private String advantage;

    private String content;
    //核心效能
    private String efficacy;
    //使用方法
    private String instructions;
    //商品介绍
    private String longPicture;
    //商品名称
    private String name;
    //提示
    private String outline;
    //适用人群
    private String people;
    //口味
    private String taste;
    //封面
    private String themeImg;
    //价格
    private Float price;
    //状态
    private Integer state;
    //二维码
    private String qrCode;
    //商品类型
    private String type;

}
