package com.fq.superparking.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 阿里云 OSS 工具类
 *
 * @author fang
 * @date 2023/12/06
 */
@Component
public class AliOSSUtil {
    private String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";

    private String accessKeyId = "LTAI5tAwTvW1nM5RWjfM5czL";

    private String accessKeySecret = "jgk59akpGZIino7Gi92wqeyo1n4nU3";

    private String bucketName = "fqdoss";

    public String upload(MultipartFile multipartFile) throws IOException {

        // 获取上传的文件的输入流
        InputStream inputStream = multipartFile.getInputStream();

        // 避免文件覆盖
        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + multipartFile.getOriginalFilename();

        // 上传到OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName,fileName,inputStream);

        // 文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;

        // 关闭ossClient
        ossClient.shutdown();
        // 返回图片路径
        return url;
    }

}
