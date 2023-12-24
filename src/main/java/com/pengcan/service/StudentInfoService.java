package com.pengcan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengcan.common.ResultCode;
import com.pengcan.dao.StudentInfoDao;
import com.pengcan.entity.Account;
import com.pengcan.entity.AdminInfo;
import com.pengcan.entity.StudentInfo;
import com.pengcan.entity.TeacherInfo;
import com.pengcan.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class StudentInfoService {

    @Autowired
    private StudentInfoDao studentInfoDao;

    public Account login(String name, String password) {
        //通过用户名和密码去数据库中查找
        StudentInfo studentInfo = studentInfoDao.findByNameAndPassword(name, password);
        //如果查出的对象是空
        if (ObjectUtils.isEmpty(studentInfo)) {
            throw new CustomException("-1", "用户名、密码或角色错误");
        }
        return studentInfo;
    }

    public void add(StudentInfo studentInfo) {
        //1.检查数据库中有无同名name
        StudentInfo dbStudentInfo = studentInfoDao.findByName(studentInfo.getName());
        if (!ObjectUtils.isEmpty(dbStudentInfo)) {//查到
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        //2.如果没有密码，设置初始密码
        if (ObjectUtils.isEmpty(studentInfo.getPassword())) {
            studentInfo.setPassword("123456");

        }
        //3.新增
        studentInfo.setLevel(3);
        studentInfoDao.insertSelective(studentInfo);
    }

    public StudentInfo findById(Long id) {
        return studentInfoDao.selectByPrimaryKey(id);

    }

    public void update(StudentInfo studentInfo) {
        //1.判断用户名是否存在
        StudentInfo dbStudentInfo = studentInfoDao.findByName(studentInfo.getName());
        if (!ObjectUtils.isEmpty(dbStudentInfo)) {//如果存在
            //判断是否为当前用户
            if (dbStudentInfo.getId().equals(studentInfo.getId())) {
                //更新
                studentInfoDao.updateByPrimaryKeySelective(studentInfo);
            } else {
                throw new CustomException(ResultCode.USER_EXIST_ERROR);
            }
        }
        studentInfoDao.updateByPrimaryKeySelective(studentInfo);
    }

    public List<StudentInfo> findAll() {
        return studentInfoDao.selectAllJoinXueyuan();

    }

    public PageInfo<StudentInfo> findPage(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<StudentInfo> infos = studentInfoDao.selectAllJoinXueyuan();
        return PageInfo.of(infos);
    }

    public PageInfo<StudentInfo> findPageName(Integer pageNum, Integer pageSize, String name) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        //根据名称模糊查询
        List<StudentInfo> list = studentInfoDao.findByNamePageJoinXueyuan(name);
        return PageInfo.of(list);
    }

    public void deleteById(Long id) {
        studentInfoDao.deleteByPrimaryKey(id);
    }
}
