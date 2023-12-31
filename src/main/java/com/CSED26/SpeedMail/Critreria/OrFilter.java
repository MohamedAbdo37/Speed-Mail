package com.csed26.speedmail.critreria;

import java.util.ArrayList;
import java.util.List;

import com.csed26.speedmail.mail.Mail;

public class OrFilter implements Filter {

    private Filter firstFilter;
    private Filter secondFilter;

    public OrFilter(Filter firstFilter, Filter secondFilter) {
        this.firstFilter = firstFilter;
        this.secondFilter = secondFilter;
    }

    @Override
    public List<Mail> apply(Mail[] mails) {
        List<Mail> firstFilterdMails = new ArrayList<>();
        List<Mail> secondFilterdMails = new ArrayList<>();
        List<Mail> filterdMails = new ArrayList<>();
        firstFilterdMails = firstFilter.apply(mails);
        secondFilterdMails = secondFilter.apply(mails);
        filterdMails.addAll(firstFilterdMails);
        firstFilterdMails = secondFilter.apply(firstFilterdMails.toArray(new Mail[0]));
        secondFilterdMails.removeAll(firstFilterdMails);
        filterdMails.addAll(secondFilterdMails);
        return filterdMails;
    }

}
