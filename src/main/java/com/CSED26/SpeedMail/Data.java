package com.csed26.speedmail;

import java.io.File;
import java.io.IOException;

import com.csed26.speedmail.critreria.TypeFilter;
import com.csed26.speedmail.mail.Mail;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Data {

    private Data() {
        throw new IllegalStateException("Data class");
    }

    private static String mailsPath = "Data/Mails/";
    private static String usersPath = "Data/Users/";
    private static String foldersPath = "Data/Folders/";
    private static String typssPath = "Data/Types/";

    // Mails Data
    public static void saveMail(Mail mail) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(mailsPath + mail.getId() + ".json");
        objectMapper.writeValue(file, mail);
        System.out.println("Mail successfully written to JSON file.");
    }

    public static Mail getMail(String id) throws IOException {
        Mail mail;
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(mailsPath + id + ".json");

        // Read JSON file
        mail = objectMapper.readValue(file, new TypeReference<Mail>() {});
        return mail;
    }

    public static synchronized void deleteMail(Mail mail) {
        File file = new File(mailsPath + mail.getId()+".json");
        file.delete();
    }

    // Users Data
    public static void saveUser(User user) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(usersPath + user.getAddress() + ".json");
        objectMapper.writeValue(file, user);
        System.out.println("User successfully written to JSON file.");
    }

    public static User getUser(String address) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(usersPath + address + ".json");

        // Read JSON file
        User user = objectMapper.readValue(file, new TypeReference<User>() {} );
        return user;
    }

    // Folders Data
    public static void saveFolder(Folder folder) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(foldersPath + folder.getId() + ".json");
        objectMapper.writeValue(file, folder);
        System.out.println("folder successfully written to JSON file.");
    }

    public static Folder getFolder(String id) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(foldersPath + id + ".json");

            // Read JSON file and map it to a list of shapee objects
            Folder folder = objectMapper.readValue(file, new TypeReference<Folder>() {});

            return folder;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void deleteFolder(Folder folder) {
        File file = new File(foldersPath + folder.getId()+".json");
        file.delete();
    }

    // Types data
    public static void saveType(TypeFilter type) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(foldersPath + type.getType() + ".json");
        objectMapper.writeValue(file, type);
        System.out.println("type successfully written to JSON file.");
    }

    public static TypeFilter getType(String typeName) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(foldersPath + typeName + ".json");

            // Read JSON file and map it to a list of shapee objects
            TypeFilter type = objectMapper.readValue(file, new TypeReference<TypeFilter>() {});

            return type;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void deleteType(TypeFilter type) {
        File file = new File(foldersPath + type.getType() + ".json");
        file.delete();
    }
}
