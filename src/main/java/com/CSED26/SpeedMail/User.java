package com.CSED26.SpeedMail;

import java.io.IOException;

import com.CSED26.SpeedMail.Mail.Mail;

public class User {

    private String adress;
    private String name;
    private String password;
    private String mainFolder;

    public User(String name, String address, String password) {
        this.name = name;
        this.password = password;
        this.adress = address;
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

    public String getAddress() {
        return adress;
    }

    public static User getUser(String address) throws IOException {
        return Data.getUser(address);
    }

    public void recive(Mail mail) throws IOException {
        this.getMainFolder().addToIndex(mail);
        ;
    }

    public void addToSend(Mail mail) throws IOException {
        this.getMainFolder().addToSend(mail);
    }

    public void addToDraft(Mail mail) throws IOException {
        this.getMainFolder().addToDraft(mail);
    }
}