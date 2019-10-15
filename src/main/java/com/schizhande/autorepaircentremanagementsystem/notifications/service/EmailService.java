package com.schizhande.autorepaircentremanagementsystem.notifications.service;

import com.schizhande.autorepaircentremanagementsystem.notifications.dto.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;


    public void sendMail(Email email) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email.getRecipients());
        message.setFrom("chizhandesheldon689@gmail.com");
        message.setSubject(email.getSubject());
        message.setText(email.getBody());

        emailSender.send(message);
    }
}
