package com.csed26.speedmail.commands;

import java.io.IOException;

import com.csed26.speedmail.User;
import com.csed26.speedmail.mail.Mail;

public class DeleteEmail implements Command {
    private Mail mail;
    private User user;

    public DeleteEmail(Mail mail, User user) {
        this.mail = mail;
        this.user = user;
    }

    @Override
    public boolean execute() {
        try {
            if (mail.getFrom().equals(user.getAddress())) {
                if (this.mail.getIsDraft())
                    user.mainFolder().removeFromDraft(this.mail);
                else
                    user.mainFolder().removeFromSend(this.mail);
            }
            else
                user.mainFolder().removeFromInbox(this.mail);
            user.mainFolder().addToTrash(mail);
        } catch (IOException e) {
            System.out.println("Faild to delete mail");
            return false;
        }
        return true;
    }

}
