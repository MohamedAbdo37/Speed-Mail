package com.CSED26.SpeedMail.commands;

import java.io.IOException;

import com.csed26.speedmail.Data;
import com.csed26.speedmail.User;
import com.csed26.speedmail.mail.Mail;

public class SendEmail implements Command {

    private Mail mail;

    public SendEmail(Mail mail) {
        this.mail = mail;
    }

    @Override
    public boolean execute() {
        try {
            User user = this.mail.getFrom();

            if (this.mail.getIsDraft())
                user.getMainFolder().removeFromDraft(this.mail);

            user.getMainFolder().addToSend(mail);

            for (String address : this.mail.getTo()) {
                Data.getUser(address).recive(this.mail);
            }

            this.mail.setIsDraft(false);
        } catch (IOException e) {
            System.out.println("faild to send");
            return false;
        }

        return true;
    }

}
