package com.zzx.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.domain.entity.Category;
import com.zzx.domain.mapper.CategoryMapper;
import com.zzx.domain.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * (Category)表服务实现类
 *
 * @author makejava
 * @since 2022-03-01 19:27:35
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}

