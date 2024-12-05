package com.fq.superparking.dao;

import com.fq.superparking.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author fang
* @description 针对表【app_order】的数据库操作Mapper
* @createDate 2023-11-13 17:31:56
* @Entity com.fq.superparking.entity.Order
*/
@Mapper
public interface OrderDao {

    Boolean deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    /**
     * 分页查询所有列表 以及条件查询
     *
     * @param orgId      合作单位 id
     * @param manageId   停车场id
     * @param plateNumber 车牌号码
     * @return {@link List}<{@link Order}>
     */
    List<Order> queryAllListByPage(@Param("orgId") Integer orgId, @Param("manageId") Integer manageId, @Param("plateNumber") String plateNumber);

    /**
     * 查询总记录数
     *
     * @return {@link Integer}
     */
    Integer getCount();


    /**
     * 查询最近七天数据
     *
     * @return {@link List}<{@link Order}>
     */
    List<Order> queryRecentlySevenSkyData();

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
