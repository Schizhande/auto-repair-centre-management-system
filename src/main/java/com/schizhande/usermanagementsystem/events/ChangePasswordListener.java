package com.schizhande.usermanagementsystem.events;

import com.schizhande.usermanagementsystem.dao.TokenRepository;
import com.schizhande.usermanagementsystem.model.Token;
import com.schizhande.usermanagementsystem.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

@Slf4j
public class ChangePasswordListener implements ApplicationListener<OnUserCreateEvent> {

    private final TokenRepository tokenRepository;

    public ChangePasswordListener(TokenRepository tokenRepository) {
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
    }
}
