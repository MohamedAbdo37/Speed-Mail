package com.CSED26.SpeedMail.Mail;

import java.util.Random;

public class Server {

    public static Server server;

    private Server() {
        throw new IllegalStateException("Server class");
    }

    public static synchronized Server getServer() {
        if (server == null)
            server = new Server();
        return server;
    }

    public static String generateId(int length) {

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
