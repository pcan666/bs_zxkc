package com.pengcan.dao;

import com.pengcan.entity.ClassInfo;
import com.pengcan.entity.ZhuanyeInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ZhuanyeInfoDao extends Mapper<ZhuanyeInfo> {

    @Select("select * from zhuanye_info where name = #{name}")
    ZhuanyeInfo findByName(String name);

    List<ZhuanyeInfo> selectAllJoinXueyuan();

    //通过姓名模糊查询
    List<ZhuanyeInfo> selectAllJoinXueyuanByName(String name);
}
