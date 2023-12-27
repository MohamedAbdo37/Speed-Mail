package com.csed26.speedmail;

import java.io.IOException;
import java.util.ArrayList;

import com.csed26.speedmail.commands.Command;
import com.csed26.speedmail.mail.Mail;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String address;
    private String name;
    private ArrayList<String> contacts;
    private Command command;
    private String password;
    private ArrayList<String> folders;

    public User(String name, String address, String password) throws IOException {
        this.name = name;
        this.password = password;
        this.address = address;
        this.folders  = new ArrayList<>();
        Folder.createNewAccount(this.address);
        this.contacts = new ArrayList<>();
        try {
            Data.saveUser(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.addFolder(Folder.inBox);
        this.addFolder("Social");
        this.addFolder("Offers");
    }

    public User(@JsonProperty("address") String address, @JsonProperty("name") String name,
            @JsonProperty("contacts") ArrayList<String> contacts,@JsonProperty("folders") ArrayList<String> folders,
            @JsonProperty("password") String password) {
        this.address = address;
        this.name = name;
        this.contacts = contacts;
        this.password = password;
        this.folders = folders;
    }

    public Folder mainFolder() throws IOException {
        return Data.getFolder(this.address);
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<String> getFolders() {
        return folders;
    }
    
    public void addFolder(String folder) throws IOException{
        this.folders.add(folder);
        Data.saveUser(this);
    }
    public static boolean checkPassword(String address, String password) {
        User user;
        try {
            user = User.user(address);
            if (password.equals(user.getPassword()))
                return true;
        } catch (IOException e) {
            System.out.println("address dose not exist");
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public static User user(String address) throws IOException {
        return Data.getUser(address);
    }

    public void recive(Mail mail) throws IOException {
        this.mainFolder().addToIndex(mail);
    }

    public void addContact(String contact) {
        this.contacts.add(contact);
    }

    public String[] contacts() {
        return (String[]) this.contacts.toArray();
    }

    public ArrayList<String> getContacts() {
        return contacts;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public boolean execute() {
        return this.command.execute();
    }

    public String getPassword() {
        return password;
    }
}