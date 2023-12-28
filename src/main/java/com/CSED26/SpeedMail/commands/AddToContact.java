package com.csed26.speedmail.commands;

import java.io.IOException;

import com.csed26.speedmail.Contact;
import com.csed26.speedmail.User;


public class AddToContact implements Command {

    private Contact contact;
    private String[] addresses;


    public AddToContact(Contact contact, String[] addresses ){
        this.contact = contact;
        this.addresses = addresses;

    }



    @Override
    public boolean execute() {

        for (String address : addresses) {
            try {
                contact.addContact(address);
            } catch (IOException e) {
                System.out.println("Contact dose not exist");
                return false;
            }
        }
        return true;
    }
    
}
