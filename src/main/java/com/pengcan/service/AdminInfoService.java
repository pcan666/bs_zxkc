package com.pengcan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengcan.common.ResultCode;
import com.pengcan.dao.AdminInfoDao;
import com.pengcan.entity.Account;
import com.pengcan.entity.AdminInfo;
import com.pengcan.entity.TeacherInfo;
import com.pengcan.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class AdminInfoService {
    @Autowired
    private AdminInfoDao adminInfoDao;

    public Account login(String name, String password) {
        //通过用户名和密码去数据库中查找
        AdminInfo adminInfo = adminInfoDao.findByNameAndPassword(name, password);

        if (ObjectUtils.isEmpty(adminInfo)) {
            throw new CustomException("-1", "用户名、密码或角色错误");
        }
        return adminInfo;
    }

    public AdminInfo findById(Long id) {

        return adminInfoDao.selectByPrimaryKey(id);
    }

    public void update(AdminInfo adminInfo) {
        //1.判断用户名是否存在
        AdminInfo dbAdminInfo = adminInfoDao.findByName(adminInfo.getName());
        if (!ObjectUtils.isEmpty(dbAdminInfo)) {//如果存在
            //判断是否为当前用户
            if (dbAdminInfo.getId().equals(adminInfo.getId())) {
                //更新
                adminInfoDao.updateByPrimaryKeySelective(adminInfo);
            } else {
                throw new CustomException(ResultCode.USER_EXIST_ERROR);
            }
        }
        adminInfoDao.updateByPrimaryKeySelective(adminInfo);
    }

    public void add(AdminInfo adminInfo) {
        //1.先查询数据库中有无同名管理员
        AdminInfo dbAdminInfo = adminInfoDao.findByName(adminInfo.getName());
        if (!ObjectUtils.isEmpty(dbAdminInfo)) {//如果查到了
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        if (ObjectUtils.isEmpty(adminInfo.getPassword())) {//如果传递给管理员无密码
            //设置初始密码
            adminInfo.setPassword("123456");

        }
        adminInfo.setLevel(1);
        adminInfoDao.insertSelective(adminInfo);
    }

    public List<AdminInfo> findAll() {
        return adminInfoDao.selectAll();

    }

    public void deleteById(Long id) {
        adminInfoDao.deleteByPrimaryKey(id);
    }

    public PageInfo<AdminInfo> findPage(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        //下面查询就会自动根据pageNum和pageSize来查对应的数据
        List<AdminInfo> adminInfos = adminInfoDao.selectAll();
        return PageInfo.of(adminInfos);
    }

    public PageInfo<AdminInfo> findPageName(Integer pageNum, Integer pageSize, String name) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<AdminInfo> adminInfosPage = adminInfoDao.findByNamePage(name);
        return PageInfo.of(adminInfosPage);

    }
}
