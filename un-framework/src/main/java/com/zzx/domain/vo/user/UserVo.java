package com.zzx.domain.vo.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-03-03 17:40
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    //主键
    private Long id;
    //昵称
    private String nickName;
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

}
