package com.fq.superparking.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class SendEmailUtil {

    private static final SendEmailUtil sendEmailUtil = new SendEmailUtil();
    @Resource
    JavaMailSender javaMailSender;

    @PostConstruct
    public void init(){
        sendEmailUtil.javaMailSender = javaMailSender;
        sendEmailUtil.usernameEmail = usernameEmail;
    }

    @Value("${spring.mail.username}")
    String usernameEmail;

    /**
     * 发送电子邮件
     *
     * @param addresseeEmail 收件人电子邮件
     * @param text           文本
     */
    public static void sendEmail(String[] addresseeEmail,String subject,String text) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = sendEmailUtil.javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
        messageHelper.setSubject(subject);
        messageHelper.setText("<h1 style=\"color: red\">"+text+"</h1>");
        messageHelper.setSentDate(new Date());
        messageHelper.setFrom(sendEmailUtil.usernameEmail);
        messageHelper.setTo(addresseeEmail);

        sendEmailUtil.javaMailSender.send(message);
    }

}
