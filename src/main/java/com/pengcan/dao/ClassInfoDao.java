package com.pengcan.dao;

import com.pengcan.entity.ClassInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ClassInfoDao extends Mapper<ClassInfo> {

    List<ClassInfo> findAllPageName(String name);

    @Select("select * from class_info where name = #{name} ;")
    ClassInfo findByName(String name);

    List<ClassInfo> selectAllJoinZHuanyeAndTeacher();

    List<ClassInfo> selectAllByNameJoinTeacherAndZHuanye(String name);

    @Select("select * from class_info where name = #{name} and teacherId = #{teacherId}")
    ClassInfo findByNameAndTeacher(@Param("name") String name, @Param("teacherId") Long teacherId);
}
