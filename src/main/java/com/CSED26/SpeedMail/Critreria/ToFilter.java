package com.csed26.speedmail.critreria;

import com.csed26.speedmail.mail.Mail;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ToFilter implements Filter {
    private String toCriterion;

    public ToFilter(String toCriterion) {
        this.toCriterion = toCriterion;
    }

    @Override
    public List<Mail> apply(List<Mail> mails) {
        List<Mail> filterdMails = new ArrayList<>();
        if (mails != null) {
            for (Mail mail : mails) {
                if (mail.getTo().equals(toCriterion))
                    filterdMails.add(mail);
            }
        }
        return filterdMails;
    }

}
