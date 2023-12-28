package com.csed26.speedmail.mail;

import java.io.File;
import java.io.IOException;

import com.csed26.speedmail.Data;

public class Builder {
    private Mail mail;

    public Builder(String sender) {
        this.mail = new Mail(sender);
    }

    public void buildTo(String[] addresses) {
        for (String address : addresses) {
            this.mail.addRecipient(address);
        }
    }

    public void buildType(String[] types) {
        for (String type : types) {
            this.mail.addType(type);
        }
    }

    public void buildAttachmet(File[] files) {
        this.mail.addAttachment(files);
    }

    public void buildSubject(String subject) {
        this.mail.setSubject(subject);
    }

    public void buildBody(String body) {
        this.mail.setBody(body);
    }

    public void buildDate(String date) {
        this.mail.setDate(date);
    }

    public void buildPriority(int priority) {
        this.mail.setPriority(priority);
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
