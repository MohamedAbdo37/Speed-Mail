package com.csed26.speedmail.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        try {
            user.mainFolder().removeFromContact(contact);
            List<String> contacts = user.getContacts();
            contacts.remove(contact.getName());
            user.setContacts((ArrayList<String>) contacts);
        } catch (IOException e) {
            System.out.println("User dose not exist");
            return false;
        }
        return true;
    }
    
}
