package com.pengcan.dao;

import com.pengcan.entity.TeacherInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TeacherInfoDao extends Mapper<TeacherInfo> {
    @Select("select * from teacher_info where name = #{name} and password = #{password}")
    TeacherInfo findByNameAndPassword(@Param("name") String name,@Param("password") String password);

    @Select("select * from teacher_info where name = #{name} ;")
    TeacherInfo findByName(@Param("name") String name);

    List<TeacherInfo> findByNamePage(String name);

    List<TeacherInfo> selectAllJoinZhuanye();

    List<TeacherInfo> selectAllByNameJoinZhuanye(String name);
}
