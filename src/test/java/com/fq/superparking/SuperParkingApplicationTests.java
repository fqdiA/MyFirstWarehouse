package com.fq.superparking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@SpringBootTest
class SuperParkingApplicationTests {

    @Resource
    JavaMailSender javaMailSender;
    @Test
    void contextLoads() throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject("这个是标题");
        mimeMessageHelper.setSentDate(new Date());
        mimeMessageHelper.setText("<h1 style=\"color: red\">这个是正文</h1>");
        mimeMessageHelper.setFrom("2659754209@qq.com");
        mimeMessageHelper.setTo("2659754209@qq.com");

        javaMailSender.send(mimeMessage);
    }

}
