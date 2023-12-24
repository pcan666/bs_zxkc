package com.pengcan.controller;

import com.github.pagehelper.PageInfo;
import com.pengcan.common.Result;
import com.pengcan.entity.XueyuanInfo;
import com.pengcan.service.XueyuanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/xueyuanInfo")
public class XueyuanInfoController {
    @Autowired
    private XueyuanInfoService xueyuanInfoService;

    //更新
    @PutMapping
    public Result update(@RequestBody XueyuanInfo xueyuanInfo){

        xueyuanInfoService.update(xueyuanInfo);
        return Result.success();
    }

    //查询所有
    @GetMapping
    public Result findALl(){
        List<XueyuanInfo> list = xueyuanInfoService.findAll();
        return Result.success(list);
    }
    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        PageInfo<XueyuanInfo> info = xueyuanInfoService.findPage(pageNum, pageSize);
        return Result.success(info);
    }
    //分页条件查询
    @GetMapping("/page/{name}")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@PathVariable String name){
        PageInfo<XueyuanInfo> info = xueyuanInfoService.findPageName(pageNum, pageSize,name);
        return Result.success(info);
    }
    //新增
    @PostMapping
    public Result add(@RequestBody XueyuanInfo xueyuanInfo){
        xueyuanInfoService.add(xueyuanInfo);
        return Result.success();
    }
    //删除用户
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        xueyuanInfoService.deleteById(id);
        return Result.success();
    }
}
