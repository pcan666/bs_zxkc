package com.pengcan.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "zhuanye_info")
@Data
public class ZhuanyeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "department")
    private String department;
    @Column(name = "xueyuanId")
    private Long xueyuanId;
    @Transient
    private String xueyuanName;//学院名称
}
