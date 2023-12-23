package com.csed26.speedmail.commands;

import java.io.IOException;

import com.csed26.speedmail.mail.Mail;

public class SendEmail implements Command {

    private Mail mail;

    public SendEmail(Mail mail){
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
