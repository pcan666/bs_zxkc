package com.pengcan.controller;

import com.github.pagehelper.PageInfo;
import com.pengcan.common.Result;
import com.pengcan.entity.AdminInfo;
import com.pengcan.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("adminInfo")
public class AdminInfoController {

    @Autowired
    private AdminInfoService adminInfoService;

    //修改管理员个人信息
    @PutMapping
    public Result update(@RequestBody AdminInfo adminInfo){
        adminInfoService.update(adminInfo);
        return Result.success();
    }
    //新增管理员
    @PostMapping
    public Result add(@RequestBody AdminInfo adminInfo){
        adminInfoService.add(adminInfo);
        return Result.success();
    }
    //查询管理员数据
    @GetMapping
    public Result findAll(){
        List<AdminInfo> adminInfos =  adminInfoService.findAll();
        return Result.success(adminInfos);
    }
    //删除
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable Long id){
        adminInfoService.deleteById(id);
        return Result.success();
    }
    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        PageInfo<AdminInfo> info = adminInfoService.findPage(pageNum, pageSize);
        return Result.success(info);
    }
    //分页条件查询
    @GetMapping("/page/{name}")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@PathVariable String name){
        PageInfo<AdminInfo> info = adminInfoService.findPageName(pageNum, pageSize,name);
        return Result.success(info);
    }
}
