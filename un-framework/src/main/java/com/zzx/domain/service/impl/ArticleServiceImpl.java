package com.zzx.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Article;
import com.zzx.domain.entity.Category;
import com.zzx.domain.entity.User;
import com.zzx.domain.mapper.ArticleMapper;
import com.zzx.domain.service.ArticleService;
import com.zzx.domain.service.CategoryService;
import com.zzx.domain.service.UserService;
import com.zzx.domain.vo.PageVo;
import com.zzx.domain.vo.article.ArticleVo;
import com.zzx.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * (Article)表服务实现类
 *
 * @author makejava
 * @since 2022-03-01 19:14:35
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseResult hotArticleList() {
        //查询热门的文章，根据阅读数来排序
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getReader);
        //分页查询，最多显示五条记录
        Page<Article> page = new Page<>(1,5);
        List<Article> articleList = page(page, queryWrapper).getRecords();
        //封装vo
        List<ArticleVo> hotArticleVos = toArticleVo(articleList);
        return ResponseResult.okResult(new PageVo(hotArticleVos, page.getTotal()));
    }

    private List<ArticleVo> toArticleVo(List<Article> articleList) {
        List<ArticleVo> articleVos = BeanCopyUtils.copyBeanList(articleList, ArticleVo.class);
        Set<Long> categoryIds = new HashSet<>();
        Set<Long> userIds = new HashSet<>();
        //从 articleList 里取出分类 id 赋给 ids，因为有可能重复，所以选择 set集合
        articleList.stream()
                .map(article -> {
                    categoryIds.add(article.getCategoryId());
                    userIds.add(article.getCreateBy());
                    return article;
                })
                .collect(Collectors.toList());
        //根据 ids 集合查询相关信息，赋值给vo集合里的对应属性；
        Map<Long,Category> categoryMap = new HashMap<>();
        Map<Long,User> userMap = new HashMap<>();
        List<Category> categoryList = categoryService.listByIds(categoryIds);
        List<User> userList = userService.listByIds(userIds);
        for (Category category : categoryList) {
            categoryMap.put(category.getId(), category);
        }
        for (User user : userList) {
            userMap.put(user.getId(), user);
        }
        articleVos.stream()
                .map(ArticleVo -> {
                    ArticleVo.setCategoryName(categoryMap.get(ArticleVo.getCategoryId()).getName());
                    ArticleVo.setCreateName(userMap.get(ArticleVo.getCreateBy()).getNickName());
                    ArticleVo.setCreateAvatar(userMap.get(ArticleVo.getCreateBy()).getAvatar());
                    return ArticleVo;
                }).collect(Collectors.toList());
        return articleVos;
    }

    @Override
    public ResponseResult articleList(Long categoryId) {
        //根据分类id查询相关文章
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getCategoryId,categoryId);
        List<Article> articleList = list(queryWrapper);
        //封装vo
        List<ArticleVo> articleVos = toArticleVo(articleList);
        return ResponseResult.okResult(articleVos);
    }
}

