package com.pengcan.controller;

import com.github.pagehelper.PageInfo;
import com.pengcan.common.Result;
import com.pengcan.entity.TeacherInfo;
import com.pengcan.service.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teacherInfo")
public class TeacherInfoController {
    @Autowired
    private TeacherInfoService teacherInfoService;

    //更新
    @PutMapping
    public Result update(@RequestBody TeacherInfo teacherInfo){

        teacherInfoService.update(teacherInfo);
        return Result.success();
    }

    //查询所有
    @GetMapping
    public Result findALl(){
        List<TeacherInfo> list = teacherInfoService.findAll();
        return Result.success(list);
    }
    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        PageInfo<TeacherInfo> info = teacherInfoService.findPage(pageNum, pageSize);
        return Result.success(info);
    }
    //分页条件查询
    @GetMapping("/page/{name}")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@PathVariable String name){
        PageInfo<TeacherInfo> info = teacherInfoService.findPageName(pageNum, pageSize,name);
        return Result.success(info);
    }
    //新增
    @PostMapping
    public Result add(@RequestBody TeacherInfo teacherInfo){
        teacherInfoService.add(teacherInfo);
        return Result.success();
    }
    //删除用户
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        teacherInfoService.deleteById(id);
        return Result.success();
    }
}
