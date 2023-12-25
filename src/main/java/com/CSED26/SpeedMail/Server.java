package com.csed26.speedmail;

import java.io.IOException;
import java.util.Random;

public class Server {

    public static Server server;
    private boolean free = true;

    private Server(){
        throw new IllegalStateException("Server class");
    }

    // public static synchronized Server getServer(){
    //     if (server == null)
    //             server = new Server();
    //     return server;
    // }

    public static synchronized Server acquire(){
        if(!server.free)
            return null;
        server.free = false;
        return server;
    }

    void release(){
        this.free = true;;
    }

    public synchronized User logIn(String address, String password) throws IOException{
        if(User.checkPassword(address ,password))
            return null;
        
        return Data.getUser(address);
    }

    public synchronized User register(String name ,String address, String password) throws IOException{
        try {
            Data.getUser(address);
        } catch (IOException e) {
            return new User(name, address, password);
        }
        return null;
    }


    public static String generateId(int length){
        
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        String sb = new String();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb += String.valueOf(randomChar);
        }

        return sb;
    }
}
