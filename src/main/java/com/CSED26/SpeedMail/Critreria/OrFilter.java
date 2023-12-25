package com.CSED26.SpeedMail.Critreria;

import java.util.ArrayList;
import java.util.List;

import com.CSED26.SpeedMail.Mail.Mail;

public class OrFilter implements Filter {

    private Filter firstFilter;
    private Filter secondFilter;

    public OrFilter(Filter firstFilter, Filter secondFilter) {
        this.firstFilter = firstFilter;
        this.secondFilter = secondFilter;
    }

    @Override
    public List<Mail> apply(List<Mail> mails) {
        List<Mail> filterdMails = new ArrayList<>();
        filterdMails = firstFilter.apply(mails);
        filterdMails = secondFilter.apply(filterdMails);
        return filterdMails;
    }

}
