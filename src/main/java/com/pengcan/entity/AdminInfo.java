package com.pengcan.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "admin_info")
@Data
public class AdminInfo extends Account{
    @Column(name = "phone")
    private String phone;
}
