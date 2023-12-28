package com.csed26.speedmail.commands;

import com.csed26.speedmail.Contact;
import com.csed26.speedmail.Folder;
import com.csed26.speedmail.User;

public class DeleteContact implements Command {

    private User user;
    private Contact contact;

    public DeleteContact(User user, Contact contact){
        this.user = user;
        this.contact = contact;
    }

    @Override
    public boolean execute() {
        // user.mainFolder().removeFrom(Folder.contacts,contact);
        return true;
    }
    
}
