package com.pengcan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengcan.common.ResultCode;
import com.pengcan.dao.TeacherInfoDao;
import com.pengcan.dao.ZhuanyeInfoDao;
import com.pengcan.entity.*;
import com.pengcan.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class TeacherInfoService {
    @Autowired
    private TeacherInfoDao teacherInfoDao;

    @Autowired
    private ZhuanyeInfoDao zhuanyeInfoDao;

//    public void register(TeacherInfo teacherInfo) {
//        //1.判断用户名是否存在
//        TeacherInfo dbTeacherInfo = teacherInfoDao.findByName(teacherInfo.getName());
//        if (!ObjectUtils.isEmpty(dbTeacherInfo)) {//如果不为空
//            throw new CustomException(ResultCode.USER_EXIST_ERROR);
//        }
//        //2.如果没有注册
//        teacherInfoDao.insert(teacherInfo);
//    }

    public Account login(String name, String password) {
        //通过用户名和密码去数据库中查找
        TeacherInfo dbTeacherInfo = teacherInfoDao.findByNameAndPassword(name, password);

        if (ObjectUtils.isEmpty(dbTeacherInfo)) {
            throw new CustomException("-1", "用户名、密码或角色错误");
        }
        return dbTeacherInfo;
    }

    public TeacherInfo findById(Long id) {
        return teacherInfoDao.selectByPrimaryKey(id);
    }

    public void update(TeacherInfo teacherInfo) {
        //1.判断用户名是否存在
        TeacherInfo dbTeacherInfo = teacherInfoDao.findByName(teacherInfo.getName());
        if (!ObjectUtils.isEmpty(dbTeacherInfo)) {//如果存在
            //判断是否为当前用户
            if (dbTeacherInfo.getId().equals(teacherInfo.getId())) {
                //更新
                teacherInfoDao.updateByPrimaryKeySelective(teacherInfo);
            } else {
                throw new CustomException(ResultCode.USER_EXIST_ERROR);
            }
        }
        teacherInfoDao.updateByPrimaryKeySelective(teacherInfo);
    }

    public List<TeacherInfo> findAll() {
        return teacherInfoDao.selectAll();
    }

    public void add(TeacherInfo teacherInfo) {

        //1.检查数据库中有无同名name
        TeacherInfo dbTeacherInfo = teacherInfoDao.findByName(teacherInfo.getName());
        if (!ObjectUtils.isEmpty(dbTeacherInfo)) {//查到
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        //2.如果没有密码，设置初始密码
        if (ObjectUtils.isEmpty(teacherInfo.getPassword())) {
            teacherInfo.setPassword("123456");
        }
        //3.新增
        teacherInfo.setLevel(2);
        teacherInfoDao.insertSelective(teacherInfo);
    }

    public void deleteById(Long id) {
        teacherInfoDao.deleteByPrimaryKey(id);
    }

    public PageInfo<TeacherInfo> findPage(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<TeacherInfo> list = teacherInfoDao.selectAllJoinZhuanye();

//        for (TeacherInfo teacherInfo : list) {
//            ZhuanyeInfo dbZhuanyeInfo = zhuanyeInfoDao.selectByPrimaryKey(teacherInfo.getZhuanyeId());
//            teacherInfo.setZhuanyeName(dbZhuanyeInfo.getName());
//        }

        return PageInfo.of(list);
    }

    public PageInfo<TeacherInfo> findPageName(Integer pageNum, Integer pageSize, String name) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<TeacherInfo> list = teacherInfoDao.selectAllByNameJoinZhuanye(name);
        //报错（频繁查询数据库）
//        for (TeacherInfo teacherInfo : list) {
//            ZhuanyeInfo dbZhuanyeInfo = zhuanyeInfoDao.selectByPrimaryKey(teacherInfo.getZhuanyeId());
//            teacherInfo.setZhuanyeName(dbZhuanyeInfo.getName());
//        }

        return PageInfo.of(list);
    }
}
