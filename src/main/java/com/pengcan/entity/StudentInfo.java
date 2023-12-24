package com.pengcan.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "student_info")
@Data
public class StudentInfo extends Account {
    @Column(name = "code")
    private String code;
    @Column(name = "xueyuanId")
    private String xueyuanId;
    @Transient
    private String xueyuanName;
    @Column(name = "score")
    private Integer score;
}
