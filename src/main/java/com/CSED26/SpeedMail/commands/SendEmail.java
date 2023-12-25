package com.CSED26.SpeedMail.commands;

import java.io.IOException;

import com.CSED26.SpeedMail.Mail.Mail;

public class SendEmail implements Command {

    private Mail mail;

    public SendEmail(Mail mail) {
        this.mail = mail;
    }

    @Override
    public boolean execute() {
        try {
            this.mail.send();
        } catch (IOException e) {
            System.out.println("faild to send");
            return false;
        }
        return true;
    }

}
