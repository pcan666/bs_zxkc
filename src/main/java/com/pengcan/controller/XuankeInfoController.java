package com.pengcan.controller;

import com.pengcan.common.Result;
import com.pengcan.entity.XuankeInfo;
import com.pengcan.service.XuankeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/xuankeInfo")
public class XuankeInfoController {
    @Autowired
    private XuankeInfoService xuankeInfoService;

    @GetMapping
    public Result findAll(HttpServletRequest request) {
        List<XuankeInfo> list = xuankeInfoService.findAll(request);
        return Result.success(list);
    }

    //退课
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        xuankeInfoService.delete(id);
        return Result.success();
    }
    //开课
    @PutMapping
    public Result update(@RequestBody XuankeInfo xuankeInfo){
        xuankeInfoService.update(xuankeInfo);
        return Result.success();
    }

}
