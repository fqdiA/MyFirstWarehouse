package com.fq.superparking.service.impl;

import cn.hutool.core.date.DateUtil;
import com.fq.superparking.dao.OrderDao;
import com.fq.superparking.entity.Order;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class OrderServiceImplTest {

    @Resource
    OrderDao orderDao;

    @Test
    void queryOrderAmount() {

        List<Order> orderList = orderDao.queryRecentlySevenSkyData();
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < 7; i++) {

            Date date = DateUtils.addDays(new Date(), -i);
            String formatDate = sdf.format(date);
            dateList.add(formatDate);
        }

        Map<String, BigDecimal> Wechat = new HashMap<>();
        Map<String, BigDecimal> Alipay = new HashMap<>();

        for (int i = 0; i < dateList.size(); i++) {
            for (int j = 0; j < orderList.size(); j++) {
                String s = dateList.get(i);
                String substring1 = DateUtil.format(orderList.get(j).getGmtCreate(), "yyyy-MM-dd").substring(0, 10);
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
        System.out.println(Wechat);
        System.out.println(Alipay);
    }

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(0);
        BigDecimal tt = new BigDecimal(1);
        BigDecimal add = bigDecimal.add(tt);
        System.out.println(add);
    }

    @Test
    void queryAlipayOrderAmount() {
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
        Order order = new Order();
        Alipay.forEach((key,value)->{
            System.out.println(key+"===="+value);
            try {
                order.setGmtCreate(sdf.parse(key));
                order.setTotalFee(value);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            orders.add(order);
            System.out.println(orders);
        });
        System.out.println(Alipay);
    }
}