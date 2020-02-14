package com.schizhande.usermanagementsystem.notifications.service;

import com.schizhande.usermanagementsystem.exceptions.InvalidRequestException;
import com.schizhande.usermanagementsystem.notifications.dto.Email;
import com.schizhande.usermanagementsystem.notifications.formatter.EmailFormatter;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public abstract class EmailNotifierTemplate {

    private final EmailService emailService;

    protected String subject;

    protected Set<String> recipient = new HashSet<>();

    protected EmailFormatter emailFormatter = new EmailFormatter();

    private Email email = new Email();

    protected EmailNotifierTemplate(EmailService emailService) {
        this.emailService = emailService;
    }

    private void buildMessage(){
        if(isNull(emailFormatter.getMessage())){
            throw new InvalidRequestException("Email body is required");
        }

        this.email = Email.builder()
                .subject(this.subject)
                .body(emailFormatter.getMessage().toString())
                .build();
    }

    public void sendEmail(){

        this.buildMessage();

        if(nonNull(this.recipient)){
            this.recipient.forEach(recipient->{
                this.email.setRecipients(recipient);
                emailService.sendMail(this.email);
            });
        }
    }
}
