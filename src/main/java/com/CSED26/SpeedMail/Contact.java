package com.csed26.speedmail;

import java.io.IOException;
import java.util.ArrayList;

public class Contact {

    private String id;
    private String name;
    private ArrayList<String> addresses;

    public Contact(String name, String[] adresses) throws IOException {
        this.id = Server.generateId(10);
        this.name = name;
        this.addresses = new ArrayList<>();
        for (String a : adresses) {
            this.addresses.add(a);
        }
        Data.saveContact(this);
    }

    public void addContact(String address) throws IOException{
        this.addresses.add(address);
        Data.saveContact(this);
    }

    public void removeContact(String address) throws IOException{
        this.addresses.remove(address);
        Data.saveContact(this);
    }
    // Getters
    public ArrayList<String> getAddresses() {
        return addresses;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setAddresses(ArrayList<String> addresses) {
        this.addresses = addresses;
    }

    public void setName(String name) {
        this.name = name;
    }

    // search

    public boolean nameHas(String content) {
        if (this.name.contains(content))
            return true;
        return false;
    }

    public boolean addressesHas(String content){
        for (String address : addresses)
            if (address.contains(content))
                return true;
        
        return false;
    }

}
