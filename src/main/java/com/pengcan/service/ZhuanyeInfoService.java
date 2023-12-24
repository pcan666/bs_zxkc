package com.pengcan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pengcan.common.ResultCode;
import com.pengcan.dao.ZhuanyeInfoDao;
import com.pengcan.entity.XueyuanInfo;
import com.pengcan.entity.ZhuanyeInfo;
import com.pengcan.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ZhuanyeInfoService {

    @Autowired
    private ZhuanyeInfoDao zhuanyeInfoDao;

    public void update(ZhuanyeInfo zhuanyeInfo) {
        //1.判断用户名是否存在
        ZhuanyeInfo dbZhuanyeInfo = zhuanyeInfoDao.findByName(zhuanyeInfo.getName());
        if (!ObjectUtils.isEmpty(dbZhuanyeInfo)) {//如果存在
            //判断是否为当前用户
            if (dbZhuanyeInfo.getId().equals(zhuanyeInfo.getId())) {
                //更新
                zhuanyeInfoDao.updateByPrimaryKeySelective(zhuanyeInfo);
            } else {
                throw new CustomException(ResultCode.USER_EXIST_ERROR);
            }
        }
        zhuanyeInfoDao.updateByPrimaryKeySelective(zhuanyeInfo);
    }

    public List<ZhuanyeInfo> findAll() {
        return zhuanyeInfoDao.selectAll();
    }

    public PageInfo<ZhuanyeInfo> findPage(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<ZhuanyeInfo> infos = zhuanyeInfoDao.selectAllJoinXueyuan();
        return PageInfo.of(infos);
    }

    public PageInfo<ZhuanyeInfo> findPageName(Integer pageNum, Integer pageSize, String name) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<ZhuanyeInfo> infos = zhuanyeInfoDao.selectAllJoinXueyuanByName(name);
        return PageInfo.of(infos);
    }

    public void add(ZhuanyeInfo zhuanyeInfo) {
        zhuanyeInfoDao.insertSelective(zhuanyeInfo);
    }

    public void deleteById(Long id) {
        zhuanyeInfoDao.deleteByPrimaryKey(id);
    }
}
