package com.fq.superparking.utils;

import com.baidu.aip.ocr.AipOcr;
import com.fq.superparking.config.BaiduProperties;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;

/**
 * ocr车牌上传工具类
 *
 * @author fang
 * @date 2023/10/28
 */
@Component
public class OcrUtil {

    private static final OcrUtil INSTANCE = new OcrUtil();

    @Resource
    private BaiduProperties baiduProperties;


    @PostConstruct
    public void init(){
        INSTANCE.baiduProperties = baiduProperties;
    }

    public static String plateLicense(byte[] image){
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(INSTANCE.baiduProperties.getAppId(),INSTANCE.baiduProperties.getApiKey(),INSTANCE.baiduProperties.getSecretKey());
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("multi_detect", "false");  // 是否要一次识别多张车牌
        // 调用接口
        JSONObject res = client.plateLicense(image, options);
        System.out.println(res);
        System.out.println(res.toString(2));
        JSONObject wordsResult = res.getJSONObject("words_result");
        String number = wordsResult.getString("number");

        return number;
    }

}
