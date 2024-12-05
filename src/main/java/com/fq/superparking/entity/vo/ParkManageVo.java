package com.fq.superparking.entity.vo;

import lombok.Data;


/**
 * 往前端传递的数据
 * @author fang
 * @version 1.0.0
 * @date 2023/10/28
 */
@Data
public class ParkManageVo {
    /**
     * 停车场id
     */
    private Long id;
    /**
     * 停车场名字
     */
    private String name;
}
