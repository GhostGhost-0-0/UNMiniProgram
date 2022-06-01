package com.zzx.domain.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-03-22 19:14
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {

    private String token;
    private UserInfoVo userInfoVo;
}
