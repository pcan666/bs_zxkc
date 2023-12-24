package com.pengcan.service;

import com.pengcan.dao.*;
import com.pengcan.entity.*;
import com.pengcan.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class XuankeInfoService {
    @Autowired
    private XuankeInfoDao xuankeInfoDao;
    @Autowired
    private ZhuanyeInfoDao zhuanyeInfoDao;
    @Autowired
    private TeacherInfoDao teacherInfoDao;
    @Autowired
    private StudentInfoDao studentInfoDao;
    @Autowired
    private ClassInfoDao classInfoDao;

    public List<XuankeInfo> findAll(HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (ObjectUtils.isEmpty(user)) {
            throw new CustomException("-1", "登录失效，请重新登录");
        }

        List<XuankeInfo> list;

        //判断当前角色是哪个角色
        if (1 == user.getLevel()) {
            //管理员
            list = xuankeInfoDao.selectAll();
        } else if (2 == user.getLevel()) {
            //教师
            list = xuankeInfoDao.findByCondition(user.getId(), null);
        } else {
            //学生
            list = xuankeInfoDao.findByCondition(null, user.getId());
        }

        for (XuankeInfo xuankeInfo : list) {
            ZhuanyeInfo zhuanyeInfo = zhuanyeInfoDao.selectByPrimaryKey(xuankeInfo.getZhuanyeId());
            TeacherInfo teacherInfo = teacherInfoDao.selectByPrimaryKey(xuankeInfo.getTeacherId());
            StudentInfo studentInfo = studentInfoDao.selectByPrimaryKey(xuankeInfo.getStudentId());
            xuankeInfo.setZhuanyeName(zhuanyeInfo.getName());
            xuankeInfo.setTeacherName(teacherInfo.getName());
            xuankeInfo.setStudentName(studentInfo.getName());
        }
        return list;
    }

    public void add(XuankeInfo xuankeInfo) {
        xuankeInfoDao.insertSelective(xuankeInfo);
    }

    public XuankeInfo find(String name, Long teacherId, Long studentId) {

        return xuankeInfoDao.find(name, teacherId, studentId);
    }

    public void delete(Integer id) {
        XuankeInfo dbXuankeInfo = xuankeInfoDao.selectByPrimaryKey(id);
        ClassInfo dbClassInfo = classInfoDao.findByNameAndTeacher(dbXuankeInfo.getName(), dbXuankeInfo.getTeacherId());
        xuankeInfoDao.deleteByPrimaryKey(id);
        dbClassInfo.setYixuan(dbClassInfo.getYixuan() - 1);
        classInfoDao.updateByPrimaryKeySelective(dbClassInfo);
    }

    public void update(XuankeInfo xuankeInfo) {
        xuankeInfoDao.updateByPrimaryKeySelective(xuankeInfo);
    }
}
