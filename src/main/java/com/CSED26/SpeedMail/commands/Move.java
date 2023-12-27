package com.csed26.speedmail.commands;

import java.io.IOException;

import com.csed26.speedmail.Folder;
import com.csed26.speedmail.User;
import com.csed26.speedmail.mail.Mail;

public class Move implements Command {

    private User user;
    private String source;
    private String dest;
    private Mail mail;

    public Move(User user ,String source, String dest, Mail mail){
        this.user = user;
        this.source = source;
        this.dest = dest;
        this.mail = mail;
    }

    @Override
    public boolean execute() {
        Folder folder;
        try {
            folder = user.mainFolder();
            folder.removeFrom(source, mail);
            folder.addTo(dest, mail);
        } catch (IOException e) {
            System.out.println("Faild to move");
            return false;
        } catch (Exception e) {
            System.out.println("Faild to move");
            return false;
        }

        return true;
    }

}
