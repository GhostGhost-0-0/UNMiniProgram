package com.zzx.domain.vo.scheme;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-03-01 14:33
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchemeVo {
    //主键id
    private Long id;
    //方案名
    private String name;
    //提示信息
    private String outline;
    //二维码
    private String qrCode;
    //图片
    private String themeImg;

}
