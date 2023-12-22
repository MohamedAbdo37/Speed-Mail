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

    private String getPassword() {
        return password;
    }

    public static boolean checkPassword(String address, String password){
        User user = User.getUser(address);
        if(password.equals(user.getPassword()))
            return true;
        return false;
    }
    
    public String getName() {
        return name;
    }
    public String getAdress() {
        return adress;
    }
    
    public static User getUser(String address) {
        return null;
    }
    
}
