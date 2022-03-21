package com.zzx.domain.vo.action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-02-27 19:40
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionVo {

    //主键，动作id
    private Long id;
    //第几个动作
    private Integer classNo;
    //封面
    private String detailsCover;
    //标题
    private String title;
}
