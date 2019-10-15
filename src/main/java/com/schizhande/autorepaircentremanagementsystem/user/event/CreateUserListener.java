package com.schizhande.autorepaircentremanagementsystem.user.event;

import com.schizhande.autorepaircentremanagementsystem.notifications.service.EmailNotifierTemplate;
import com.schizhande.autorepaircentremanagementsystem.notifications.service.EmailService;
import com.schizhande.autorepaircentremanagementsystem.user.dao.TokenRepository;
import com.schizhande.autorepaircentremanagementsystem.user.model.Token;
import com.schizhande.autorepaircentremanagementsystem.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

@Slf4j
public class CreateUserListener extends EmailNotifierTemplate implements ApplicationListener<OnUserCreateEvent> {

    private final TokenRepository tokenRepository;

    public CreateUserListener(EmailService emailService, TokenRepository tokenRepository) {
        super(emailService);
        this.tokenRepository = tokenRepository;
    }


    @Override
    public void onApplicationEvent(OnUserCreateEvent onUserCreateEvent) {

        User user = onUserCreateEvent.getUser();
        String token = UUID.randomUUID().toString();
        Token myToken = new Token(token, user);
        myToken.calculateExpiryDate(60);
        tokenRepository.save(myToken);

        log.info("----->User token {}", token);
        log.info("----->User {}", user);

        subject = "User account verification";

        recipient.add(user.getEmail());

        emailFormatter.addParagraph("Verify your account using this link");

        emailFormatter.addLink("Localhost:8080");

        //sendEmail();


    }
}
