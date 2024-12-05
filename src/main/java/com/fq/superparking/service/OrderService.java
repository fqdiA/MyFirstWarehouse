package com.fq.superparking.service;

import com.fq.superparking.common.R;
import com.fq.superparking.entity.Order;
import com.fq.superparking.entity.vo.OrderVo;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderService {

    /**
     * 页面列表
     * 分页查询订单列表
     *
     * @param orgId      合作单位id
     * @param manageId   停车场id
     * @param platNumber 车牌号码 模糊查询
     * @param pageNum    当前页
     * @param pageSize   页面大小
     * @return {@link R}
     */
    R pageList(Integer pageNum, Integer pageSize, Integer orgId, Integer manageId, String platNumber);

    /**
     * 按id删除订单
     *
     * @param orderId 订单id
     * @return {@link R}<{@link Boolean}>
     */
    R<String> deleteOrderById(Long orderId);

    /**
     * 查询微信最近7天订单金额
     *
     * @return {@link R}<{@link List}<{@link Order}>>
     */
    R<List<Order>> queryWechatOrderAmount();

    /**
     * 查询支付宝最近7天订单金额
     *
     * @return {@link R}<{@link List}<{@link Order}>>
     */
    R<List<Order>> queryAlipayOrderAmount();

    /**
     * 查询微信总记录数
     *
     * @return {@link Integer}
     */
    Integer queryWeChatCount();

    /**
     * 查询支付宝总记录数
     *
     * @return {@link Integer}
     */
    Integer queryAlipayCount();

    /**
     * 得到总收益
     *
     * @return {@link Double}
     */
    Double getCountMoney();
}
