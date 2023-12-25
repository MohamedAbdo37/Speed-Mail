package com.CSED26.SpeedMail.Mail;

import java.io.File;
import java.io.IOException;

import com.CSED26.SpeedMail.Data;
import com.CSED26.SpeedMail.User;
import com.CSED26.SpeedMail.Types;

public class Builder {
    private Mail mail;

    public Builder(User sender) {
        this.mail = new Mail(sender.getAddress());
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
