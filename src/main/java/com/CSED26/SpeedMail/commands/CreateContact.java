package com.csed26.speedmail.commands;

import java.io.IOException;
import java.util.List;

import com.csed26.speedmail.Contact;
import com.csed26.speedmail.Data;
import com.csed26.speedmail.Folder;
import com.csed26.speedmail.User;

public class CreateContact implements Command {

    private User user;
    private String name;
    private String[] adresses;
    public CreateContact(User user, String name , List<String> adresses){
        this.user = user;
        this.name = name;
        this.adresses = adresses.toArray(new String[0]);
    }
    @Override
    public boolean execute() {
        Folder contactsFolder;
        Contact contact;
        try {
            contact = new Contact(name, adresses);
            user.mainFolder().addContact(contact);
        } catch (IOException e) {
            System.out.println("Contacts Folder dose not exist");
            return false;
        }

        user.addContact(name);
        try {
            Data.saveUser(user);
        } catch (IOException e) {
            System.out.println("User dose not exist");
            return false;
        }
        return true;
    }
    
}
