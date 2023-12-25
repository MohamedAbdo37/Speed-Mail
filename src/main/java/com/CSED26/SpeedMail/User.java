package com.csed26.speedmail;

import java.io.IOException;

import com.csed26.speedmail.mail.Mail;

public class User {

    private String address;
    private String name;
    private String password;
    private String mainFolder;

    public User(String name, String address, String password) throws IOException {
        this.name = name;
        this.password = password;
        this.address = address;
        this.mainFolder = Folder.createNewAccount().getId();
        try {
            Data.saveUser(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Folder getMainFolder() throws IOException {
        return Data.getFolder(this.mainFolder);
    }

    private String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public static boolean checkPassword(String address, String password) {
        User user;
        try {
            user = User.getUser(address);
            if (password.equals(user.getPassword()))
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public static User getUser(String address) throws IOException {
        return Data.getUser(address);
    }

    public void recive(Mail mail) throws IOException{
        this.getMainFolder().addToIndex(mail);;
    }

}