package com.fq.superparking.entity.vo;

import com.fq.superparking.entity.CarManage;

/**
 * 车辆管理vo
 *
 * @author fang
 * @date 2023/10/28
 */
public class CarManageVo extends CarManage {
    /**
     * 类型名称
     */
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
