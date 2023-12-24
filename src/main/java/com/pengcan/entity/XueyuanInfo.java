package com.pengcan.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

import javax.persistence.*;

@Table(name = "xueyuan_info")
@Data
public class XueyuanInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String score;
}
