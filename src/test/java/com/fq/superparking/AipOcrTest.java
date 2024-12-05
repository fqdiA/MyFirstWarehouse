package com.fq.superparking;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AipOcrTest {
    //设置APPID/AK/SK
    public static final String APP_ID = "41873653";
    public static final String API_KEY = "lHNR5hFAfFgwtSzMYqXWkLoP";
    public static final String SECRET_KEY = "GHqwwhZaZvUoXZYkUG9podlq2GZlvtNI";

    public static void main(String[] args) throws JSONException {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("multi_detect", "false");  // 是否要一次识别多张车牌
        // 调用接口
        String image = "C:\\Users\\fang\\Desktop\\01.png";
        JSONObject res = client.plateLicense(image, options);

        System.out.println(res.toString(2));

    }

}
