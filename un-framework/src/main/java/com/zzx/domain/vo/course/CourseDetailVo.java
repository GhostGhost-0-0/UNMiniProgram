package com.zzx.domain.vo.course;

import com.zzx.domain.vo.action.ActionVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-02-27 19:28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailVo {

    //主键，课程的id
    private Long id;
    //课程训练的总动作数
    private Integer classNum;
    //课程的学习人数
    private Integer learnNum;
    //课程的学习难度
    private Integer level;
    //口号
    private String outline;
    //运动类型
    private String sportType;
    //运动类型详情
    private String sportTypeDesc;
    //标题
    private String title;
    //顶部图片
    private String topImg;
    //所属分类id
    private Long categoryId;
    //动作简要介绍
    private List<ActionVo> action;
}
