package com.CSED26.SpeedMail.Mail;

import com.CSED26.SpeedMail.User;
import com.CSED26.SpeedMail.Critreria.CritreriaIF;

public class Builder {
    private Mail mail;

    public Builder(User sender){
        this.mail = new Mail(sender);
    }

    public void buildTo(String[] addresses){
        for (String address : addresses) {
            this.mail.addRecipient(User.getUser(address));
        }
    }

    public void buildType(String[] types){
        for (String type : types) {
            this.mail.addType(CritreriaIF.getCritreria(type));
        }
    }

    public void buildSubject(String subject){
        this.mail.setSubject(subject);
    }

    public void buildBody(String body){
        this.mail.setSubject(body);
    }

    public Mail getMail(){
        return this.mail;
    }

}
