package com.ceragem.iot.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class CoreMailService {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Value("${project.properties.email-from}")
    String from;


    public void sendHtml(String to, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // MimeMessageHelper messageHelper = new MimeMessageHelper(message, null != file, charSet);
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(content, true);
//        if (null != file) {
//            File[] var11 = file;
//            int var12 = file.length;
//
//            for(int var13 = 0; var13 < var12; ++var13) {
//                File atFile = var11[var13];
//                DataSource dataSource = new FileDataSource(atFile);
//                messageHelper.addAttachment(MimeUtility.encodeText(atFile.getName(), charSet, "B"), dataSource);
//            }
//        }
        javaMailSender.send(mimeMessage);
    }
}
