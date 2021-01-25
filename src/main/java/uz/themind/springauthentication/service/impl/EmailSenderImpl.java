package uz.themind.springauthentication.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.themind.springauthentication.exception.EmailConnectionException;
import uz.themind.springauthentication.service.EmailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender mailSender;

    @Value(value = "${spring.mail.username}")
    private String gmailAddress;

    private final static Logger logger = LoggerFactory.getLogger(EmailSenderImpl.class);

    public EmailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(String to, String message) {

        try {
            SimpleMailMessage mimeMessage=new SimpleMailMessage();
            mimeMessage.setText(message);
            mimeMessage.setTo(to);
            mimeMessage.setSubject("Confirm your email");
            mimeMessage.setFrom(gmailAddress);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            logger.error("failed to send email: " + e.getMessage());
            throw new EmailConnectionException(to);
        }
    }
}
