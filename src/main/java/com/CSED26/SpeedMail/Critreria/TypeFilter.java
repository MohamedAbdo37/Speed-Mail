package com.csed26.speedmail.critreria;

import java.util.ArrayList;
import java.util.List;

import com.csed26.speedmail.mail.Mail;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TypeFilter implements Filter {

    private String type;

    public TypeFilter(@JsonProperty("type") String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public List<Mail> apply(Mail[] mails) {
        List<Mail> filterdMails = new ArrayList<>();
        if (mails != null) {
            for (Mail mail : mails) {
                if (mail.searchForType(type)) {
                    filterdMails.add(mail);
                }
            }
        }
        return filterdMails;
    }

}
