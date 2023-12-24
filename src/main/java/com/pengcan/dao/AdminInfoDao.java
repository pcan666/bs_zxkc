package com.pengcan.dao;

import com.pengcan.entity.AdminInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AdminInfoDao extends Mapper<AdminInfo> {

    @Select("select * from admin_info where name=#{name} and password = #{password} ;")
    AdminInfo findByNameAndPassword(@Param("name") String username,@Param("password") String password);

    @Select("select * from admin_info where name = #{name}")
    AdminInfo findByName(@Param("name") String name);

    List<AdminInfo> findByNamePage(@Param("name") String name);
}