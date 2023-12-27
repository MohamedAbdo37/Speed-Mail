package com.csed26.speedmail;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.csed26.speedmail.critreria.AndFilter;
import com.csed26.speedmail.critreria.Filter;
import com.csed26.speedmail.critreria.FromFilter;
import com.csed26.speedmail.critreria.OrFilter;
import com.csed26.speedmail.critreria.ToFilter;
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
        mail = objectMapper.readValue(file, new TypeReference<Mail>() {
        });
        return mail;
    }

    public static synchronized void deleteMail(Mail mail) {
        File file = new File(mailsPath + mail.getId() + ".json");
        file.delete();
    }

    // Users Data
    public static void saveUser(User user) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(usersPath + user.getAddress() + ".json");
        objectMapper.writeValue(file, user);
        System.out.println("User "+user.getAddress()+" successfully written to JSON file.");
    }

    public static User getUser(String address) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(usersPath + address + ".json");

        // Read JSON file
        User user = objectMapper.readValue(file, new TypeReference<User>() {
        });
        return user;
    }

    // Folders Data
    public static void saveFolder(Folder folder) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(foldersPath + folder.getId() + ".json");
        objectMapper.writeValue(file, folder);
        System.out.println("folder "+folder.getFolderName()+" successfully written to JSON file.");
    }

    public static Folder getFolder(String id) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(foldersPath + id + ".json");

            // Read JSON file and map it to a list of shapee objects
            Folder folder = objectMapper.readValue(file, new TypeReference<Folder>() {
            });

            return folder;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void deleteFolder(Folder folder) {
        File file = new File(foldersPath + folder.getId() + ".json");
        file.delete();
    }

    // Contacts Data
    public static void saveContact(Contact contact) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(foldersPath + contact.getId() + ".json");
        objectMapper.writeValue(file, contact);
        System.out.println("Contact "+contact.getName()+" successfully written to JSON file.");
    }

    public static Contact getContact(String id) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(foldersPath + id + ".json");

            // Read JSON file and map it to a list of shapee objects
            Contact contact = objectMapper.readValue(file, new TypeReference<Contact>() {
            });

            return contact;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void deleteContact(Contact contact) {
        File file = new File(foldersPath + contact.getId() + ".json");
        file.delete();
        System.out.println("Contact " +contact.getName()+" successfully deleted.");
    }

    // Types data
    public static void saveType(TypeFilter type) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(foldersPath + type.getType() + ".json");
        objectMapper.writeValue(file, type);
        System.out.println("type " + type.getType() + " successfully written to JSON file.");
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
        System.out.println("type " + type.getType() + " successfully deleted.");
    }

    public static Mail[] filterMailsByTo(String toString, Mail[] mails) {
        Filter toFilter = new ToFilter(toString);
        List<Mail> filterdMails = toFilter.apply(mails);
        return filterdMails.toArray(new Mail[0]);
    }

    public static Mail[] filterMailsByFrom(String fromString, Mail[] mails) {
        Filter fromFilter = new FromFilter(fromString);
        List<Mail> filterdMails = fromFilter.apply(mails);

        return filterdMails.toArray(new Mail[0]);
    }

    public static Mail[] filterMailsByType(String typeString, Mail[] mails) {
        Filter typeFilter = new TypeFilter(typeString);
        List<Mail> filterdMails = typeFilter.apply(mails);

        return filterdMails.toArray(new Mail[0]);
    }

    public static Mail[] filterBy(String filterBy, String filterString, Mail[] mails) {
        Mail[] filterdMails = null;
        if (filterBy.equalsIgnoreCase("fromfilter")) {
            filterdMails = filterMailsByFrom(filterString, mails);
        } else if (filterBy.equalsIgnoreCase("tofilter")) {
            filterdMails = filterMailsByTo(filterString, mails);
        } else if (filterBy.equalsIgnoreCase("typefilter")) {
            filterdMails = filterMailsByType(filterString, mails);
        } else {
            throw new UnsupportedOperationException();
        }
        return filterdMails;
    }

    public static Mail[] andFilterMails(String firstCri, String firstCriString, String secondCri,
            String secondCriString, Mail[] mails) {
        Filter firstFilter;
        Filter secondFilter;
        if (firstCri.equalsIgnoreCase("fromfilter")) {
            firstFilter = new FromFilter(firstCriString);
        } else if (firstCri.equalsIgnoreCase("tofilter")) {
            firstFilter = new ToFilter(firstCriString);
        } else if (firstCri.equalsIgnoreCase("typefilter")) {
            firstFilter = new TypeFilter(firstCriString);
        } else {
            throw new UnsupportedOperationException();
        }
        if (secondCri.equalsIgnoreCase("fromfilter")) {
            secondFilter = new FromFilter(secondCriString);
        } else if (secondCri.equalsIgnoreCase("tofilter")) {
            secondFilter = new ToFilter(secondCriString);
        } else if (secondCri.equalsIgnoreCase("typefilter")) {
            secondFilter = new TypeFilter(secondCriString);
        } else {
            throw new UnsupportedOperationException();
        }
        Filter andFilter = new AndFilter(firstFilter, secondFilter);
        List<Mail> filterdMails = andFilter.apply(mails);

        return filterdMails.toArray(new Mail[0]);
    }

    public static Mail[] orFilterMails(String firstCri, String firstCriString, String secondCri,
            String secondCriString, Mail[] mails) {
        Filter firstFilter;
        Filter secondFilter;
        if (firstCri.equalsIgnoreCase("fromfilter")) {
            firstFilter = new FromFilter(firstCriString);
        } else if (firstCri.equalsIgnoreCase("tofilter")) {
            firstFilter = new ToFilter(firstCriString);
        } else if (firstCri.equalsIgnoreCase("typefilter")) {
            firstFilter = new TypeFilter(firstCriString);
        } else {
            throw new UnsupportedOperationException();
        }
        if (secondCri.equalsIgnoreCase("fromfilter")) {
            secondFilter = new FromFilter(secondCriString);
        } else if (secondCri.equalsIgnoreCase("tofilter")) {
            secondFilter = new ToFilter(secondCriString);
        } else if (secondCri.equalsIgnoreCase("typefilter")) {
            secondFilter = new TypeFilter(secondCriString);
        } else {
            throw new UnsupportedOperationException();
        }
        Filter orFilter = new OrFilter(firstFilter, secondFilter);
        List<Mail> filterdMails = orFilter.apply(mails);

        return filterdMails.toArray(new Mail[0]);
    }

}
