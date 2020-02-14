package com.schizhande.usermanagementsystem.notifications.formatter;

import lombok.Data;

@Data
public class EmailFormatter {

    private StringBuilder message;

    public EmailFormatter() {
        message = new StringBuilder();
    }

    public void addParagraph(String text){
        this.message.append("<p>" + text + "</p>");
    }

    public void addLink(String link){
        this.message.append("<p><a>" + link + "</a></p>");
    }

    public static String bold(String text){
        return "<b>" + text + "</b>";
    }

    public static String italic(String text){
        return "<i>" + text + "</i>";
    }

}
