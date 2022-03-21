package com.zzx.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.entity.Course;


/**
 * (Courses)表服务接口
 *
 * @author makejava
 * @since 2022-02-27 15:06:47
 */
public interface CourseService extends IService<Course> {

    ResponseResult hotCourseList();

    ResponseResult courseList();

    ResponseResult courseDetail(Long id);
}

