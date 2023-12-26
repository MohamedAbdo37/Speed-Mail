package com.csed26.speedmail.critreria;

import java.util.ArrayList;
import java.util.List;

import com.csed26.speedmail.mail.Mail;

public class TypeFilter implements Filter {

    private String type;

    public TypeFilter(String type) {
        this.type = type;
    }

    @Override
    public List<Mail> apply(List<Mail> mails) {
        List<Mail> filterdMails = new ArrayList<>();
        if (mails != null) {
            for (Mail mail : mails) {
                if (mail.getTypes().searchForType(type)) {
                    filterdMails.add(mail);
                }
            }
        }
        return filterdMails;
    }

}
