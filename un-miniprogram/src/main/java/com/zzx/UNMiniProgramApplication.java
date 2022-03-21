package com.zzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-02-26 14:30
 **/
@SpringBootApplication
@MapperScan("com.zzx.domain.mapper")
public class UNMiniProgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(UNMiniProgramApplication.class, args);
    }
}
