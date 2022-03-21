package com.zzx.controller;

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
    public ResponseResult hotCourseList() {
        return coursesService.hotCourseList();
    }

    @GetMapping("/courseList")
    public ResponseResult courseLitt() {
        return coursesService.courseList();
    }

    @GetMapping("/courseDetail")
    public ResponseResult courseDetail(@RequestParam("courseId") Long id) {
        return coursesService.courseDetail(id);
    }
}
