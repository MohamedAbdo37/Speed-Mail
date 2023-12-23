package com.csed26.speedmail.mail;

import java.io.IOException;

import com.csed26.speedmail.Data;
import com.csed26.speedmail.User;
import com.csed26.speedmail.critreria.CritreriaIF;

public class Builder {
    private Mail mail;

    public Builder(User sender) {
        this.mail = new Mail(sender.getAddress());
    }

    public void buildTo(String[] addresses){
        for (String address : addresses) {
            this.mail.addRecipient(address);
        }
    }

    public void buildType(String[] types) {
        for (String type : types) {
            this.mail.addType(CritreriaIF.getCritreria(type));
        }
    }

    public void buildSubject(String subject) {
        this.mail.setSubject(subject);
    }

    public void buildBody(String body) {
        this.mail.setSubject(body);
    }

    public Mail getMail() {
        try {
            Data.saveMail(this.mail);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.mail;
    }

}
