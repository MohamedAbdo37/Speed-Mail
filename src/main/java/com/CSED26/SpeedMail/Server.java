package com.csed26.speedmail;

import java.io.IOException;
import java.util.Random;

import com.csed26.speedmail.mail.Mail;

public class Server implements ServerIF {

    public static Server server;

    private Server() {
    }

    public static synchronized Server getServer() {
        if (server == null)
            server = new Server();
        return server;
    }

    public User logIn(String address, String password) throws IOException {
        if (User.checkPassword(address, password))
            return Data.getUser(address);

        throw new IOException("Wrong password");
    }

    public User register(String name, String address, String password) throws IOException {
        try {
            Data.getUser(address);
        } catch (IOException e) {
            return new User(name, address, password);
        }
        return null;
    }

    public static String generateId(int length) {

        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        String sb = "";
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb += String.valueOf(randomChar);
        }

        return sb;
    }

    @Override
    public Mail getMail(String id) {
        try {
            return Data.getMail(id);
        } catch (IOException e) {
            System.out.println("Mail dosen't exist!");
            return null;
        }
    }

    @Override
    public Mail[] getFolder(String folderName, String address) {
        Folder folder;
        try {
            folder = Data.getFolder(address).folder(folderName);
        } catch (IOException e) {
            System.out.println("folder dosen't exist");
            return new Mail[0];
        }

        String[] mailsId = folder.Mails();
        Mail[] mails = new Mail[mailsId.length];
        for (int i = 0; i < mails.length; i++) {
            mails[i] = this.getMail(mailsId[i]);
        }
        return mails;
    }

    
}
