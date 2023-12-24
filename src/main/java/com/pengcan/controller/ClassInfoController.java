package com.pengcan.controller;

import com.github.pagehelper.PageInfo;
import com.pengcan.common.Result;
import com.pengcan.entity.Account;
import com.pengcan.entity.ClassInfo;
import com.pengcan.entity.XuankeInfo;
import com.pengcan.exception.CustomException;
import com.pengcan.service.ClassInfoService;
import com.pengcan.service.XuankeInfoService;
import com.pengcan.service.XueyuanInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("classInfo")
public class ClassInfoController {

    @Autowired
    private ClassInfoService classInfoService;
    @Autowired
    private XuankeInfoService xuankeInfoService;


    @PutMapping
    public Result update(@RequestBody ClassInfo classInfo){
        classInfoService.update(classInfo);
        return Result.success();
    }
    //查询所有
    @GetMapping
    public Result findALl(){
        List<ClassInfo> list = classInfoService.findAll();
        return Result.success(list);
    }
    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        PageInfo<ClassInfo> info = classInfoService.findPage(pageNum, pageSize);
        return Result.success(info);
    }
    //分页条件查询
    @GetMapping("/page/{name}")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@PathVariable String name){
        PageInfo<ClassInfo> info = classInfoService.findPageName(pageNum, pageSize,name);
        return Result.success(info);
    }
    //新增
    @PostMapping
    public Result add(@RequestBody ClassInfo classInfo){
        classInfoService.add(classInfo);
        return Result.success();
    }
    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        classInfoService.deleteById(id);
        return Result.success();
    }
    @PostMapping("/xuanke")
    public Result xuanke(@RequestBody ClassInfo classInfo, HttpServletRequest request){

        //0.先判断已选人数是否达到开班人数（前端实现）

        //1.拷贝到选课信息中
        XuankeInfo xuankeInfo = new XuankeInfo();
        BeanUtils.copyProperties(classInfo, xuankeInfo);
        //清空ID
        xuankeInfo.setId(null);
        //2.将选课信息补全
        Account user =(Account) request.getSession().getAttribute("user");
        if (ObjectUtils.isEmpty(user)) {
            throw new CustomException("-1","登录失效，请重新登录");
        }

        //判断该学生有无选过这门课
        XuankeInfo dbXuankeInfo = xuankeInfoService.find(classInfo.getName(),classInfo.getTeacherId(),user.getId());
        if(!ObjectUtils.isEmpty(dbXuankeInfo)){
            throw new CustomException("-1","你已经选过了该门课程了哦~");
        }

        xuankeInfo.setStudentId(user.getId());
        xuankeInfo.setStatus("待开课");

        xuankeInfoService.add(xuankeInfo);
        //3.课程信息已选人数+1
        classInfo.setYixuan(classInfo.getYixuan() + 1);
        classInfoService.update(classInfo);

        return Result.success();
    }
}
