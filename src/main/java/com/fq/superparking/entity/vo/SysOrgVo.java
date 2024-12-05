package com.fq.superparking.entity.vo;


import lombok.Data;

@Data
public class SysOrgVo {
    private Long orgId;
    private Long  parentId;
    private String  code;
    private String  name;
    private String  fullName;
    private String  director;
    private String  email;
    private String  phone;
    private String  address;
    private String  orderNum;
    private Integer status;
}
