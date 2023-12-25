package com.CSED26.SpeedMail.Critreria;

import java.util.ArrayList;
import java.util.List;

import com.CSED26.SpeedMail.Mail.Mail;

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
