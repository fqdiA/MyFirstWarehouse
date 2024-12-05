package com.fq.superparking;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import com.fq.superparking.config.PromotionConfig;
import com.fq.superparking.utils.TimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@SpringBootTest
public class test {
    @Test
    void test(){
        List<String> list = Arrays.asList("1", "2", "3", "4", "5", "");
        List<String> collect = list.stream().limit(3).collect(Collectors.toList()); // 查询前3个元素
        List<String> collect1 = list.stream().skip(3).collect(Collectors.toList());// 跳过前3个元素
        System.out.println(collect1);
    }

    @Test
    void test2(){
        // stream流 排序
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(list.stream().sorted((x, y) -> -x.compareTo(y)).collect(Collectors.toList()));
    }

    @Test
    void test3(){
        List<Integer> list = Arrays.asList(2, 1, 4, 3, 5);
        list.stream().sorted((x,y)->-x.compareTo(y)).forEach(System.out::println);
    }

    @Test
    void test4(){
        Integer a = 123;
        System.out.println(Convert.digitToChinese(a));  // 数字转中文
        System.out.println(SecureUtil.md5("1234")); // 密码md5加密
    }


    @Resource
    private Executor discountSolutionExecutor;

    @Test
    void test5(){
        System.out.println("开始毫秒：："+System.currentTimeMillis());
        CompletableFuture.supplyAsync(() -> {
            System.out.println("子线程开始");
            for (int i = 0; i < 10; i++) {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "，i的值：" + i);
            }
            System.out.println("子线程结束");
            return 1;
        }, discountSolutionExecutor);
        System.out.println("结束毫秒：：" + System.currentTimeMillis());
    }


}
