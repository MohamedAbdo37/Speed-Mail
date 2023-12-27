package com.csed26.speedmail.commands;

import java.io.IOException;

import com.csed26.speedmail.mail.Mail;


public class DeleteEmail implements Command {
    private Mail mail;

    public DeleteEmail(Mail mail) {
        this.mail = mail;
    }

    @Override
    public boolean execute() {
        try {
            if (this.mail.getIsDraft()) 
                this.mail.fromUser().mainFolder().removeFromDraft(this.mail);
            else
                this.mail.fromUser().mainFolder().removeFromSend(this.mail);

            this.mail.fromUser().mainFolder().addToTrash(mail);
        } catch (IOException e) {
            System.out.println("Faild to delete mail");
            return false;
        }
        return true;
    }

}
