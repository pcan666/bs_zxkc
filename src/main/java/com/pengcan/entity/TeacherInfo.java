package com.pengcan.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "teacher_info")
@Data
public class TeacherInfo extends Account{
    @Column(name = "zhicheng")
    private String zhicheng;
    @Column(name = "zhuanyeId")
    private Long zhuanyeId;
    @Transient
    private  String zhuanyeName;
}
