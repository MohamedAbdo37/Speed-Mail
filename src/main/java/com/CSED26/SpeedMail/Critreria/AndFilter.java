package com.csed26.speedmail.critreria;

import java.util.ArrayList;
import java.util.List;

import com.csed26.speedmail.mail.Mail;

public class AndFilter implements Filter {
    private Filter firstFilter;
    private Filter secondFilter;

    public AndFilter(Filter firstFilter, Filter secondFilter) {
        this.firstFilter = firstFilter;
        this.secondFilter = secondFilter;
    }

    @Override
    public List<Mail> apply(Mail[] mails) {
        List<Mail> filterdMails = new ArrayList<>();
        filterdMails = firstFilter.apply(mails);
        filterdMails = secondFilter.apply(filterdMails.toArray(new Mail[0]));
        return filterdMails;

    }
}
