package com.pengcan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengcan.common.ResultCode;
import com.pengcan.dao.XueyuanInfoDao;

import com.pengcan.entity.XueyuanInfo;
import com.pengcan.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class XueyuanInfoService {

    @Autowired
    private XueyuanInfoDao xueyuanInfoDao;

    public XueyuanInfo findById(Long id) {
        return xueyuanInfoDao.selectByPrimaryKey(id);
    }

    public void update(XueyuanInfo xueyuanInfo) {
        //1.判断用户名是否存在
        XueyuanInfo dbXueyuanInfo = xueyuanInfoDao.findByName(xueyuanInfo.getName());
        if (!ObjectUtils.isEmpty(dbXueyuanInfo)) {//如果存在
            //判断是否为当前用户
            if (dbXueyuanInfo.getId().equals(xueyuanInfo.getId())) {
                //更新
                xueyuanInfoDao.updateByPrimaryKeySelective(xueyuanInfo);
            } else {
                throw new CustomException(ResultCode.USER_EXIST_ERROR);
            }
        }
        xueyuanInfoDao.updateByPrimaryKeySelective(xueyuanInfo);
    }

    public List<XueyuanInfo> findAll() {
        return xueyuanInfoDao.selectAll();
    }

    public void add(XueyuanInfo xueyuanInfo) {

        //1.检查数据库中有无同名name
        XueyuanInfo dbXueyuanInfo = xueyuanInfoDao.findByName(xueyuanInfo.getName());
        if (!ObjectUtils.isEmpty(dbXueyuanInfo)) {//查到
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }

        //3.新增
        xueyuanInfoDao.insertSelective(xueyuanInfo);
    }

    public void deleteById(Long id) {
        xueyuanInfoDao.deleteByPrimaryKey(id);
    }

    public PageInfo<XueyuanInfo> findPage(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<XueyuanInfo> list = xueyuanInfoDao.selectAll();
        return PageInfo.of(list);
    }

    public PageInfo<XueyuanInfo> findPageName(Integer pageNum, Integer pageSize, String name) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<XueyuanInfo> list = xueyuanInfoDao.findByNamePage(name);
        return PageInfo.of(list);
    }
}
