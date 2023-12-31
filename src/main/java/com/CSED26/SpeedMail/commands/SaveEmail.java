package com.csed26.speedmail.commands;

import java.io.IOException;

import com.csed26.speedmail.Data;
import com.csed26.speedmail.mail.Mail;

public class SaveEmail implements Command{

    private Mail mail;

    public SaveEmail(Mail mail){
        this.mail = mail;
    }
    
    @Override
    public boolean execute() {
        try {
            this.mail.fromUser().mainFolder().addToDraft(mail);
            mail.setIsDraft(true);
            Data.saveMail(mail);
        } catch (IOException e) {
            System.out.println("Faild to save mail");
            return false;
        }
        
        return true;
    }
    
}
