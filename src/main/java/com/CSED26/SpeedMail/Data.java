package com.csed26.speedmail;

import java.io.File;
import java.io.IOException;

import com.csed26.speedmail.mail.Mail;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Data {

    private Data() {
        throw new IllegalStateException("Data class");
    }

    private static String mailsPath = "Data/Mails/";
    private static String usersPath = "Data/Users/";
    private static String foldersPath = "Data/Folders/";

    // Mails Data
    public static void saveMail(Mail mail) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(mailsPath + mail.getId());
        objectMapper.writeValue(file, mail);
        System.out.println("Mail successfully written to JSON file.");
    }

    public static Mail getMail(String id) throws IOException {
        Mail mail;
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(mailsPath + id);

        // Read JSON file
        mail = objectMapper.readValue(file, Mail.class);
        return mail;
    }

    public static synchronized void deleteMail(Mail mail) {
        File file = new File(mailsPath + mail.getId());
        file.delete();
    }

    // Users Data
    public static void saveUser(User user) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(usersPath + user.getAddress());
        objectMapper.writeValue(file, user);
        System.out.println("Shapes successfully written to JSON file.");
    }

    public static User getUser(String address) throws IOException {
        User user;
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(usersPath + address);

        // Read JSON file
        user = objectMapper.readValue(file, User.class);
        return user;
    }

    // Folders Data
    public static void saveFolder(Folder folder) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(foldersPath + folder.getId());
        objectMapper.writeValue(file, folder);
        System.out.println("Shapes successfully written to JSON file.");
    }

    public static Folder getFolder(String id) throws IOException {
        Folder folder;
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(foldersPath + id);

        // Read JSON file
        folder = objectMapper.readValue(file, Folder.class);
        return folder;
    }

    public static void deleteFolder(Folder folder) {
        File file = new File(foldersPath + folder.getId());
        file.delete();
    }

}
