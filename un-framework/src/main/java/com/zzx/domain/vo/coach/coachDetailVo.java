package com.zzx.domain.vo.coach;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-03-01 14:21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class coachDetailVo {
    //主键id
    private Long id;
    //教练名称
    private String name;
    //性别
    private String gender;
    //顶部头像
    private String topImg;
    //自我介绍
    private String content;
}
