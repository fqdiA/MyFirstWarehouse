package com.fq.superparking.dao;

import com.fq.superparking.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderDaoTest {

    @Resource
    OrderDao orderDao;

    @Test
    void queryAllListByPage() {
        List<Order> collect = orderDao.queryAllListByPage(null, null, "张").stream().map(value -> {
            System.out.println(value);
            return value;
        }).collect(Collectors.toList());
    }

    @Test
    void queryRecentlySevenSkyData() {
        orderDao.queryRecentlySevenSkyData().forEach(System.out::println);
    }
}