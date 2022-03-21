package com.zzx.domain.vo.coach;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-03-01 14:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class coachVo {
    //主键id
    private Long id;
    //教练名称
    private String name;
    //性别
    private String gender;
    //头像
    private String headImg;
    //身份
    private String type;
    //地点
    private String address;
}
