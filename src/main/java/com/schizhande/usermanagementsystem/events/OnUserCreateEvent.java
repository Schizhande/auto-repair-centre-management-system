package com.schizhande.usermanagementsystem.events;

import com.schizhande.usermanagementsystem.model.User;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Data
public class OnUserCreateEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private User user;

    public OnUserCreateEvent(User user, Locale locale, String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
