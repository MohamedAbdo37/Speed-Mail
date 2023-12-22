package com.CSED26.SpeedMail;

public class User {

    private String adress;
    private String name;
    private String password;
    private Folder allMessage;

    public User(String name, String address, String password){
        this.name = name;
        this.password = password;
        this.adress = address;
        this.allMessage = Folder.createNewAccount();
    }

    public Folder getAllMessage() {
        return allMessage;
    }

    public static User getUser(String address) {
        return null;
    }
    
}
