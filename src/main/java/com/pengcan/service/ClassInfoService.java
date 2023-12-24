package com.pengcan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengcan.common.ResultCode;
import com.pengcan.dao.ClassInfoDao;
import com.pengcan.dao.TeacherInfoDao;
import com.pengcan.dao.XueyuanInfoDao;
import com.pengcan.dao.ZhuanyeInfoDao;
import com.pengcan.entity.ClassInfo;
import com.pengcan.entity.TeacherInfo;
import com.pengcan.entity.ZhuanyeInfo;
import com.pengcan.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ClassInfoService {
    @Autowired
    private ClassInfoDao classInfoDao;
    @Autowired
    private ZhuanyeInfoDao zhuanyeInfoDao;
    @Autowired
    private TeacherInfoDao teacherInfoDao;

    public void update(ClassInfo classInfo) {
        //1.判断用户名是否存在
        ClassInfo dbClassInfo = classInfoDao.findByName(classInfo.getName());
        if (!ObjectUtils.isEmpty(dbClassInfo)) {//如果存在
            //判断是否为当前用户
            if (dbClassInfo.getId().equals(classInfo.getId())) {
                //更新
                classInfoDao.updateByPrimaryKeySelective(classInfo);
            } else {
                throw new CustomException(ResultCode.USER_EXIST_ERROR);
            }
        }
        classInfoDao.updateByPrimaryKeySelective(classInfo);
    }

    public List<ClassInfo> findAll() {
        return classInfoDao.selectAll();
    }

    public PageInfo<ClassInfo> findPage(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<ClassInfo> infos = classInfoDao.selectAllJoinZHuanyeAndTeacher();
        //1.遍历得到的结果集
        return PageInfo.of(infos);
    }

    public PageInfo<ClassInfo> findPageName(Integer pageNum, Integer pageSize, String name) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<ClassInfo> infos = classInfoDao.selectAllByNameJoinTeacherAndZHuanye(name);
        //1.遍历得到的结果集
        return PageInfo.of(infos);
    }

    //    /通过对应ID得到对应名称
    private PageInfo<ClassInfo> getClassInfoPageInfo(List<ClassInfo> infos) {
        for (ClassInfo classInfo : infos) {
            //2.将教师Id对应的教师名称赋值实体类
            TeacherInfo dbTeacherInfo = teacherInfoDao.selectByPrimaryKey(classInfo.getTeacherId());
            classInfo.setTeacherName(dbTeacherInfo.getName());
            //3.将专业Id对应的专业名称赋值到实体类
            ZhuanyeInfo zhuanyeInfo = zhuanyeInfoDao.selectByPrimaryKey(classInfo.getZhuanyeId());
            classInfo.setZhuanyeName(zhuanyeInfo.getName());
        }
        return PageInfo.of(infos);
    }

    public void add(ClassInfo classInfo) {
        classInfoDao.insert(classInfo);
    }

    public void deleteById(Long id) {
        classInfoDao.deleteByPrimaryKey(id);
    }
}
