package com.zzx.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Action;
import com.zzx.domain.entity.Course;
import com.zzx.domain.mapper.CourseMapper;
import com.zzx.domain.service.ActionService;
import com.zzx.domain.service.CourseService;
import com.zzx.domain.vo.action.ActionVo;
import com.zzx.domain.vo.course.CourseDetailVo;
import com.zzx.domain.vo.course.CourseVo;
import com.zzx.domain.vo.PageVo;
import com.zzx.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Courses)表服务实现类
 *
 * @author makejava
 * @since 2022-02-27 15:06:47
 */
@Service("coursesService")
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private ActionService actionService;

    @Override
    public ResponseResult hotCourseList() {
        //查询热门训练 封装成 ResponseResult 返回
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        //查询条件 先通过是否推荐排序，再根据学习人数排序
        queryWrapper.orderByDesc(Course::getIsRecomme);
        queryWrapper.orderByDesc(Course::getLearnNum);
        //只查询五条数据
        Page<Course> page = new Page<>(1,5);
        page(page, queryWrapper);

        //bean 拷贝
        List<CourseVo> courseVos = BeanCopyUtils.copyBeanList(page.getRecords(), CourseVo.class);
        return ResponseResult.okResult(new PageVo(courseVos, page.getTotal()));

    }

    @Override
    public ResponseResult courseList() {
        //查询全部训练
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        //根据分类id进行升序排序
        queryWrapper.orderByAsc(Course::getCategoryId);
        List<Course> courseList = list(queryWrapper);
        //封装vo
        List<CourseVo> courseVos = BeanCopyUtils.copyBeanList(courseList, CourseVo.class);
        return ResponseResult.okResult(courseVos);
    }

    @Override
    public ResponseResult courseDetail(Long id) {
        //根据id查询相关训练详情
        Course course = getById(id);
        //封装vo
        CourseDetailVo courseDetailVo = BeanCopyUtils.copyBean(course, CourseDetailVo.class);
        //查询动作里 courseId 跟该训练 id 一致的
        LambdaQueryWrapper<Action> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Action::getCourseId,courseDetailVo.getId());
        List<Action> actionList = actionService.list(queryWrapper);
        //将动作里的所需信息封装到vo中
        List<ActionVo> actionVoList = BeanCopyUtils.copyBeanList(actionList, ActionVo.class);
        //设置 courseDetailVo 的 action 属性
        courseDetailVo.setAction(actionVoList);
        return ResponseResult.okResult(courseDetailVo);
    }
}

