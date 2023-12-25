package com.csed26.speedmail.commands;

import java.io.IOException;

import com.csed26.speedmail.User;
import com.csed26.speedmail.mail.Mail;

public class Restore implements Command {

    private Mail mail;
    private User user;

    public Restore(User user, Mail mail) {
        this.mail = mail;
        this.user = user;

    }

    @Override
    public boolean execute() {
        try {
            this.user.getMainFolder().removeFromTrash(this.mail);
            if (this.mail.getIsDraft()) 
                this.user.getMainFolder().addToDraft(this.mail);
            else{
                if(this.mail.getFromAddress().equals(this.user.getAddress()))
                    this.user.getMainFolder().addToSend(this.mail);
                else
                    this.user.getMainFolder().addToIndex(this.mail);
            }  
        } catch (IOException e) {
            System.out.println("Faild to restore");
            return false;
        }
        return true;
    }

}
