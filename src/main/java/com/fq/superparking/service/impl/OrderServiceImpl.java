package com.fq.superparking.service.impl;

import cn.hutool.core.date.DateUtil;
import com.fq.superparking.common.OrderType;
import com.fq.superparking.common.R;
import com.fq.superparking.dao.OrderDao;
import com.fq.superparking.entity.Order;
import com.fq.superparking.entity.vo.OrderVo;
import com.fq.superparking.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    final OrderDao orderDao;


    @Override
    public R pageList(Integer pageNum,Integer pageSize,Integer orgId, Integer manageId, String plateNumber) {

        PageHelper.startPage(pageNum,pageSize);
        List<Order> orderList = orderDao.queryAllListByPage(orgId, manageId, plateNumber);


        List<Order> orderList1 = orderList.stream().map(order -> {
            Integer type = order.getType();
            Order order1 = new Order();
            BeanUtils.copyProperties(order,order1);
            for (OrderType value : OrderType.values()) {
                if (value.getCode().equals(type)) {
                    order1.setTypeString(value.getValue());
                }
            }
            return order1;
        }).collect(Collectors.toList());


        for (int i = 0; i < orderList1.size(); i++) {
            String typeString = orderList1.get(i).getTypeString();
            orderList.get(i).setTypeString(typeString);
        }

        PageInfo pageInfo = new PageInfo(orderList);

        if (pageInfo.getList().size() == 0) {
            return R.fail("没有此查询条件信息");
        }


        return R.ok(pageInfo);
    }

    @Override
    public R<String> deleteOrderById(Long orderId) {
        Boolean i = orderDao.deleteByPrimaryKey(orderId);
        if(!i){
            return R.fail("删除失败");
        }
        return R.ok("删除成功");
    }

    @Override
    public R<List<Order>> queryAlipayOrderAmount() {
        List<Order> orders = new ArrayList<>();
        List<Order> orderList = orderDao.queryRecentlySevenSkyData();
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < 7; i++) {

            Date date = DateUtils.addDays(new Date(), -i);
            String formatDate = sdf.format(date);
            dateList.add(formatDate);
        }

        Map<String, BigDecimal> Alipay = new HashMap<>();

        for (int i = 0; i < dateList.size(); i++) {
            for (int j = 0; j < orderList.size(); j++) {
                if(dateList.get(i).equals(DateUtil.format(orderList.get(j).getGmtCreate(),"yyyy-MM-dd").substring(0,10)) && orderList.get(j).getType() == 1){
                    String substring = DateUtil.format(orderList.get(j).getGmtCreate(), "yyyy-MM-dd").substring(0, 10);
                    if(Alipay.containsKey(substring)){
                        BigDecimal bigDecimal = new BigDecimal(String.valueOf(Alipay.get(substring)));
                        BigDecimal totalFee = new BigDecimal(String.valueOf(orderList.get(j).getTotalFee()));
                        BigDecimal add = bigDecimal.add(totalFee);
                        Alipay.put(substring,add);
                    }else{
                        Alipay.put(substring,orderList.get(j).getTotalFee());
                    }
                }
            }
        }

        Alipay.forEach((key,value)->{
            Order order = new Order();
            try {
                order.setGmtCreate(sdf.parse(key));
                order.setTotalFee(value);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            orders.add(order);
        });
        return R.ok(orders);
    }

    @Override
    public Integer queryWeChatCount() {
        return orderDao.queryWeChatCount();
    }

    @Override
    public Integer queryAlipayCount() {
        return orderDao.queryAlipayCount();
    }

    @Override
    public Double getCountMoney() {
        return orderDao.getCountMoney();
    }

    @Override
    public R<List<Order>> queryWechatOrderAmount() {
        List<Order> orders = new ArrayList<>();
        List<Order> orderList = orderDao.queryRecentlySevenSkyData();
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < 7; i++) {

            Date date = DateUtils.addDays(new Date(), -i);
            String formatDate = sdf.format(date);
            dateList.add(formatDate);
        }

        Map<String, BigDecimal> Wechat = new HashMap<>();

        for (int i = 0; i < dateList.size(); i++) {
            for (int j = 0; j < orderList.size(); j++) {
                if(dateList.get(i).equals(DateUtil.format(orderList.get(j).getGmtCreate(),"yyyy-MM-dd").substring(0,10)) && orderList.get(j).getType() == 0){
                    String substring = DateUtil.format(orderList.get(j).getGmtCreate(), "yyyy-MM-dd").substring(0, 10);
                    if(Wechat.containsKey(substring)){
                        BigDecimal bigDecimal = new BigDecimal(String.valueOf(Wechat.get(substring)));
                        BigDecimal totalFee = new BigDecimal(String.valueOf(orderList.get(j).getTotalFee()));
                        BigDecimal add = bigDecimal.add(totalFee);
                        Wechat.put(substring,add);
                    }else{
                        Wechat.put(substring,orderList.get(j).getTotalFee());
                    }
                }
            }
        }
        Wechat.forEach((key,value)->{
            Order order = new Order();
            try {
                order.setGmtCreate(sdf.parse(key));
                order.setTotalFee(value);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            orders.add(order);
        });


        return R.ok(orders);
    }

    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap();
        map.put("1235",12);
        map.forEach((key,value)->{
            System.out.println(key+"-------"+value);
        });
    }
}
