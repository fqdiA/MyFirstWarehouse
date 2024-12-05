package com.fq.superparking.entity.dto;

import lombok.Data;

/**
 * 接收前端的数据
 * @author fang
 * @version 1.0.0
 * @date 2023/10/28
 */
@Data
public class OrgDTO {

    /**
     * 组织单位 id
     */
    private Long orgId;
    /**
     * 组织单位全称
     */
    private String fullName;
}
