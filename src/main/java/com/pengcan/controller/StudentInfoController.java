package com.pengcan.controller;

import com.github.pagehelper.PageInfo;
import com.pengcan.common.Result;
import com.pengcan.entity.StudentInfo;
import com.pengcan.entity.TeacherInfo;
import com.pengcan.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentInfo")
public class StudentInfoController {

    @Autowired
    private StudentInfoService studentInfoService;

    @PutMapping
    public Result update(@RequestBody StudentInfo studentInfo){
        studentInfoService.update(studentInfo);
        return Result.success();
    }
    @PostMapping("/chongzhi")
    public Result chongzhi(@RequestBody StudentInfo studentInfo,@RequestParam Double money){
        studentInfoService.chongzhi(studentInfo,money);
        return Result.success();
    }
    //查询所有
    @GetMapping
    public Result findALl(){
        List<StudentInfo> list = studentInfoService.findAll();
        return Result.success(list);
    }
    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        PageInfo<StudentInfo> info = studentInfoService.findPage(pageNum, pageSize);
        return Result.success(info);
    }
    //分页条件查询
    @GetMapping("/page/{name}")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@PathVariable String name){
        PageInfo<StudentInfo> info = studentInfoService.findPageName(pageNum, pageSize,name);
        return Result.success(info);
    }
    //新增
    @PostMapping
    public Result add(@RequestBody StudentInfo studentInfo){
        studentInfoService.add(studentInfo);
        return Result.success();
    }
    //删除用户
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        studentInfoService.deleteById(id);
        return Result.success();
    }
}
