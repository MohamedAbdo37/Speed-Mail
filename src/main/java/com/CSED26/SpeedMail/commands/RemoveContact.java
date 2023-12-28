package com.csed26.speedmail.commands;

import java.io.IOException;

import com.csed26.speedmail.Contact;

public class RemoveContact implements Command {

    private Contact contact;
    private String address;

    public RemoveContact(Contact contact, String address) {
        this.contact = contact;
        this.address = address;
    }

    @Override
    public boolean execute() {
        try {
            contact.removeContact(address);
        } catch (IOException e) {
            System.out.println("Contact dose not exist");
            return false;
        }
        return true;
    }

}
