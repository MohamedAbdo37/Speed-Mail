package com.CSED26.SpeedMail.Critreria;

import com.CSED26.SpeedMail.Mail.Mail;

import java.util.ArrayList;
import java.util.List;

public class FromFilter implements Filter {
    private String fromCriterion;

    public FromFilter(String fromCriterion) {
        this.fromCriterion = fromCriterion;
    }

    @Override
    public List<Mail> apply(List<Mail> mails) {
        List<Mail> filterdMails = new ArrayList<>();
        if (mails != null) {
            for (Mail mail : mails) {
                try {
                    if (mail.getFrom().equals(fromCriterion))
                        filterdMails.add(mail);
                } catch (Exception IOException) {
                    System.out.println("Error: in fromFilter");
                }
            }
        }
        return filterdMails;
    }

}
