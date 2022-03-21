package com.zzx.domain.vo.article;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-03-01 19:22
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVo {
    //主键id
    private Long id;
    //标题
    private String title;
    //所属分类id
    private Long categoryId;
    //分类名
    private String categoryName;
    //封面
    private String cover;
    //阅读数
    private Long reader;
    //作者id
    private Long createBy;
    //作者昵称
    private String createName;
    //作者头像
    private String createAvatar;
    //副标题
    private String outline;
}
