package com.zzx.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Article;


/**
 * (Article)表服务接口
 *
 * @author makejava
 * @since 2022-03-01 19:14:35
 */
public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();

    ResponseResult articleList(Long categoryId);
}

