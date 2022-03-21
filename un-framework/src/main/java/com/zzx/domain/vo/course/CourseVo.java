package com.zzx.domain.vo.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-02-27 19:08
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseVo {

    //主键id
    private Long id;
    //训练的封面
    private String courseCover;
    //课程的学习人数
    private Integer learnNum;
    //标题
    private String title;
}
