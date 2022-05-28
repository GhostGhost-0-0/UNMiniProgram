package com.zzx.controller;

import com.zzx.annotation.SystemLog;
import com.zzx.domain.ResponseResult;
import com.zzx.domain.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-02-27 15:27
 **/
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService coursesService;

    @GetMapping("/hotCourseList")
    @SystemLog(businessName = "获取热门训练列表")
    public ResponseResult hotCourseList() {
        return coursesService.hotCourseList();
    }

    @GetMapping("/courseList")
    @SystemLog(businessName = "获取全部训练列表")
    public ResponseResult courseLitt() {
        return coursesService.courseList();
    }

    @GetMapping("/courseDetail")
    @SystemLog(businessName = "获取训练详情")
    public ResponseResult courseDetail(@RequestParam("courseId") Long id) {
        return coursesService.courseDetail(id);
    }
}
