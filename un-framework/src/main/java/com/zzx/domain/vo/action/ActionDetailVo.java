package com.zzx.domain.vo.action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-02-27 20:07
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionDetailVo {

    //主键，动作id
    private Long id;
    //第几个动作
    private Integer classNo;
    //描述
    private String description;
    //标题
    private String title;
    //配套教学视频
    private String video;
}
