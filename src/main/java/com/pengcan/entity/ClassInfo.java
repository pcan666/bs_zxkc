package com.pengcan.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "class_info")
@Data
public class ClassInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "zhuanyeId")
    private Long zhuanyeId;//专业ID
    @Transient
    private String zhuanyeName;//专业名称
    @Column(name = "score")
    private Integer score;//课程学分
    @Column(name = "teacherId")
    private Long teacherId;//教师ID
    @Transient
    private String teacherName;//教师姓名
    @Column(name = "kaiban")
    private String kaiban;//开班人数
    @Column(name = "yixuan")
    private Integer yixuan;//已选人数
    @Column(name = "time")
    private String time;//上课时段
    @Column(name = "location")
    private String location;//上课地点
}
