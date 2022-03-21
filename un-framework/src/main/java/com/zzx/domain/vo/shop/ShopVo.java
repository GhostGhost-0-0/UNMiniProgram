package com.zzx.domain.vo.shop;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-03-04 15:10
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopVo {
    //主键id
    private Long id;
    //商品名称
    private String name;

    private String themeImg;

}
