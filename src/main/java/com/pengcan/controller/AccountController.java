package com.pengcan.controller;

import com.pengcan.common.Result;
import com.pengcan.entity.Account;
import com.pengcan.entity.AdminInfo;
import com.pengcan.entity.StudentInfo;
import com.pengcan.entity.TeacherInfo;
import com.pengcan.service.AdminInfoService;
import com.pengcan.service.StudentInfoService;
import com.pengcan.service.TeacherInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
public class AccountController {

    @Autowired
    private AdminInfoService adminInfoService;
    @Autowired
    private TeacherInfoService teacherInfoService;
    @Autowired
    private StudentInfoService studentInfoService;

    //登录功能
    @PostMapping("/login")
    public Result login(@RequestBody Account user, HttpServletRequest request) {

        //校验数据是否有误
        if (ObjectUtils.isEmpty(user.getName()) || ObjectUtils.isEmpty(user.getPassword()) || ObjectUtils.isEmpty(user.getLevel())) {
            return Result.error("-1", "请完善输入信息");
        }
        Integer level = user.getLevel();
        Account loginUser = new Account();
        //判断其身份
        if (1 == level) {//管理员
            loginUser = adminInfoService.login(user.getName(), user.getPassword());
        } else if (2 == level) {//教师
            loginUser = teacherInfoService.login(user.getName(), user.getPassword());
        } else if (3 == level) {//学生
            loginUser = studentInfoService.login(user.getName(), user.getPassword());
        }

        //在session中把用户信息存入
        request.getSession().setAttribute("user", loginUser);

        return Result.success(loginUser);
    }

    //注册
    @PostMapping("/register")
    public Result register(@RequestBody Account user, HttpServletRequest request) {
        //TODO 二次确认密码
        //校验数据
        //校验数据是否有误
        if (ObjectUtils.isEmpty(user.getName()) || ObjectUtils.isEmpty(user.getPassword()) || ObjectUtils.isEmpty(user.getLevel())) {
            return Result.error("-1", "请完善输入信息");
        }
        //判断其身份
        Integer level = user.getLevel();
        if (2 == level) {//教师
            TeacherInfo teacherInfo = new TeacherInfo();
            BeanUtils.copyProperties(user, teacherInfo);//拷贝属性
            teacherInfoService.add(teacherInfo);
        } else if (3 == level) {//学生
            StudentInfo studentInfo = new StudentInfo();
            BeanUtils.copyProperties(user, studentInfo);//
            studentInfoService.add(studentInfo);
        }
        return Result.success();
    }

    //退出登录
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().setAttribute("user", null);//清空session
        return Result.success();
    }


    //获取当前登录账户的信息(用于展示个人信息)
    @GetMapping("/getUser")
    public Result getUser(HttpServletRequest request) {
        //先从session里面获取当前存的登录信息
        Account user = (Account) request.getSession().getAttribute("user");
        //判断当前登录的用户是什么角色
        Integer level = user.getLevel();
        //判断其身份
        if (1 == level) {//管理员
            AdminInfo adminInfo = adminInfoService.findById(user.getId());
            //将密码置空
//            adminInfo.setPassword("");

            return Result.success(adminInfo);
        } else if (2 == level) {//教师
            TeacherInfo teacherInfo = teacherInfoService.findById(user.getId());
            //将密码置空
//            teacherInfo.setPassword("");

            return Result.success(teacherInfo);
        } else if (3 == level) {//学生
            StudentInfo studentInfo = studentInfoService.findById(user.getId());

            return Result.success(studentInfo);
        }
        return Result.success(new Account());
    }
}
