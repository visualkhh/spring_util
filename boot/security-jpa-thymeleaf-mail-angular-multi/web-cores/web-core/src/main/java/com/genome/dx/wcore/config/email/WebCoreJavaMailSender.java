package com.genome.dx.wcore.config.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class WebCoreJavaMailSender {

    private final JavaMailSender javaMailSender;

    public WebCoreJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendHtml(String from, String to, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // MimeMessageHelper messageHelper = new MimeMessageHelper(message, null != file, charSet);
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
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
