package com.csed26.speedmail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.csed26.speedmail.commands.AddToContact;
import com.csed26.speedmail.commands.CreateContact;
import com.csed26.speedmail.commands.CreateFolder;
import com.csed26.speedmail.commands.DeleteContact;
import com.csed26.speedmail.commands.DeleteEmail;
import com.csed26.speedmail.commands.DeleteFolder;
import com.csed26.speedmail.commands.Move;
import com.csed26.speedmail.commands.RemoveContact;
import com.csed26.speedmail.commands.RenameFolder;
import com.csed26.speedmail.commands.Restore;
import com.csed26.speedmail.commands.SaveEmail;
import com.csed26.speedmail.commands.SendEmail;
import com.csed26.speedmail.critreria.AndFilter;
import com.csed26.speedmail.critreria.Filter;
import com.csed26.speedmail.critreria.FromFilter;
import com.csed26.speedmail.critreria.OrFilter;
import com.csed26.speedmail.critreria.ToFilter;
import com.csed26.speedmail.critreria.TypeFilter;
import com.csed26.speedmail.mail.Builder;
import com.csed26.speedmail.mail.Mail;

public class Server implements ServerIF {

    private static Server server;
    private static Random random = new Random();

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

    public Folder userMainFolder(String address) {
        try {
            return Data.getUser(address).mainFolder();
        } catch (IOException e) {
            return null;
        }
    }

    public static String generateId(int length) {

        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(String.valueOf(randomChar));
        }

        return sb.toString();
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
            switch (folderName) {
                case "Inbox":
                    folder = Data.getFolder(address).folder("Inbox");
                    break;
                case "Send":
                    folder = Data.getFolder(address).folder("Send");
                    break;
                case "Drafts":
                    folder = Data.getFolder(address).folder("Drafts");
                    break;
                case "Trash":
                    folder = Data.getFolder(address).folder("Trash");
                    break;
                default:
                    folder = Data.getFolder(address).folder("Inbox").folder(folderName);
                    break;
            }

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

    @Override
    public Mail createMail(String sender, String[] to, String[] types, File[] files, String subject, String body,
            String date, int priority) {

        Builder builder = new Builder(sender);

        if (to != new String[0])
            builder.buildTo(to);

        if (types != new String[0])
            builder.buildType(types);

        if (files != new File[0])
            builder.buildAttachmet(files);

        if (!("".equals(subject)))
            builder.buildSubject(subject);

        if (!("".equals(body)))
            builder.buildBody(body);

        if (!("".equals(date)))
            builder.buildDate(date);

        builder.buildPriority(priority);

        return builder.getMail();
    }

    @Override
    public boolean saveMail(String address, Mail mail) {
        User user;
        try {
            user = Data.getUser(address);
            user.setCommand(new SaveEmail(mail));
        } catch (IOException e) {
            return false;
        }

        return user.execute();
    }

    @Override
    public boolean sendMail(String address, Mail mail) {
        User user;
        try {
            user = Data.getUser(address);
            user.setCommand(new SendEmail(mail));
        } catch (IOException e) {
            return false;
        }

        return user.execute();
    }

    @Override
    public boolean deleteMail(String address, Mail mail) {
        User user;
        try {
            user = Data.getUser(address);
        } catch (IOException e) {
            return false;
        }
        user.setCommand(new DeleteEmail(mail, user));
        return user.execute();
    }

    @Override
    public boolean restoreMail(String address, Mail mail) {
        User user;
        try {
            user = Data.getUser(address);
        } catch (IOException e) {
            return false;
        }
        user.setCommand(new Restore(user, mail));
        return user.execute();
    }

    @Override
    public boolean moveMail(String address, String source, String dest, Mail mail) {
        User user;
        try {
            user = Data.getUser(address);
        } catch (IOException e) {
            return false;
        }
        user.setCommand(new Move(user, source, dest, mail));
        return user.execute();
    }

    @Override
    public boolean createFolder(String address, String folderName) {
        User user;
        try {
            user = Data.getUser(address);
        } catch (IOException e) {
            return false;
        }
        user.setCommand(new CreateFolder(user, folderName));
        return user.execute();
    }

    @Override
    public boolean renameFolder(String address, String oldName, String newName) {
        User user;
        Folder folder;
        try {
            user = Data.getUser(address);
            folder = user.mainFolder().folder(Folder.inBox).folder(oldName);
        } catch (IOException e) {
            return false;
        }
        user.setCommand(new RenameFolder(folder, newName, user));
        return user.execute();
    }

    @Override
    public boolean deleteFolder(String address, String folderName) {
        User user;
        Folder folder;
        try {
            user = Data.getUser(address);
            folder = user.mainFolder().folder(Folder.inBox);
        } catch (IOException e) {
            return false;
        }
        user.setCommand(new DeleteFolder(folder, folderName, user));
        return user.execute();

    }

    @Override
    public Mail[] serachMail(String address, String content, String type) {
        type = type.toLowerCase();
        Mail[] allMails;
        List<Mail> result = new ArrayList<>();
        switch (type) {
            case "sender":
                allMails = getFolder("Inbox", address);
                for (int i = 0; i < allMails.length; i++)
                    if (allMails[i].fromHas(content))
                        result.add(allMails[i]);
                break;
            case "receivers":
                allMails = getFolder(Folder.send, address);
                for (int i = 0; i < allMails.length; i++)
                    if (allMails[i].fromHas(content))
                        result.add(allMails[i]);

                allMails = getFolder(Folder.drafts, address);
                for (int i = 0; i < allMails.length; i++)
                    if (allMails[i].fromHas(content))
                        result.add(allMails[i]);

                allMails = getFolder(Folder.trash, address);
                for (int i = 0; i < allMails.length; i++)
                    if (allMails[i].fromHas(content))
                        result.add(allMails[i]);
                break;
            case "subject":
                allMails = getFolder("Inbox", address);
                for (int i = 0; i < allMails.length; i++)
                    if (allMails[i].subjectHas(content))
                        result.add(allMails[i]);

                allMails = getFolder(Folder.send, address);
                for (int i = 0; i < allMails.length; i++)
                    if (allMails[i].subjectHas(content))
                        result.add(allMails[i]);

                allMails = getFolder(Folder.drafts, address);
                for (int i = 0; i < allMails.length; i++)
                    if (allMails[i].subjectHas(content))
                        result.add(allMails[i]);

                allMails = getFolder(Folder.trash, address);
                for (int i = 0; i < allMails.length; i++)
                    if (allMails[i].subjectHas(content))
                        result.add(allMails[i]);
                break;
            default:

                allMails = getFolder("Inbox", address);
                for (int i = 0; i < allMails.length; i++)
                    if (allMails[i].bodyHas(content))
                        result.add(allMails[i]);

                allMails = getFolder(Folder.send, address);
                for (int i = 0; i < allMails.length; i++)
                    if (allMails[i].bodyHas(content))
                        result.add(allMails[i]);

                allMails = getFolder(Folder.drafts, address);
                for (int i = 0; i < allMails.length; i++)
                    if (allMails[i].bodyHas(content))
                        result.add(allMails[i]);

                allMails = getFolder(Folder.trash, address);
                for (int i = 0; i < allMails.length; i++)
                    if (allMails[i].bodyHas(content))
                        result.add(allMails[i]);
                break;
        }

        return result.toArray(new Mail[0]);
    }

    @Override
    public Contact[] serachContact(String address, String content, String type) {
        type = type.toLowerCase();
        Contact[] allContacts;
        List<Contact> result = new ArrayList<>();
        switch (type) {
            case "name":
                allContacts = getContacts(address);
                for (int i = 0; i < allContacts.length; i++)
                    if (allContacts[i].nameHas(content))
                        result.add(allContacts[i]);
                break;

            default:
                allContacts = getContacts(address);
                for (int i = 0; i < allContacts.length; i++)
                    if (allContacts[i].addressesHas(content))
                        result.add(allContacts[i]);
                break;
        }

        return result.toArray(new Contact[0]);
    }

    @Override
    public Mail[] filterMailsByTo(String toString, Mail[] mails) {
        Filter toFilter = new ToFilter(toString);
        List<Mail> filterdMails = toFilter.apply(mails);
        return filterdMails.toArray(new Mail[0]);
    }

    @Override
    public Mail[] filterMailsByFrom(String fromString, Mail[] mails) {
        Filter fromFilter = new FromFilter(fromString);
        List<Mail> filterdMails = fromFilter.apply(mails);

        return filterdMails.toArray(new Mail[0]);
    }

    @Override
    public Mail[] filterMailsByType(String typeString, Mail[] mails) {
        Filter typeFilter = new TypeFilter(typeString);
        List<Mail> filterdMails = typeFilter.apply(mails);

        return filterdMails.toArray(new Mail[0]);
    }

    @Override
    public Mail[] filterBy(String filterBy, String filterString, Mail[] mails) {
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

    @Override
    public Mail[] andFilterMails(String firstCri, String firstCriString, String secondCri,
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

    @Override
    public Mail[] orFilterMails(String firstCri, String firstCriString, String secondCri,
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

    @Override
    public Contact[] getContacts(String address) {
        Folder folder = null;
        try {
            folder = Data.getFolder(address).folder("Inbox");
        } catch (IOException e) {
            System.out.println("folder dosen't exist");
            return new Contact[0];
        }
        String[] contactsId = folder.Mails();
        Contact[] contacts = new Contact[contactsId.length];
        for (int i = 0; i < contacts.length; i++) {
            contacts[i] = this.getContact(contactsId[i]);
        }
        return contacts;
    }

    public String[] userFolders(String address) {
        User user;
        try {
            user = Data.getUser(address);
        } catch (IOException e) {
            System.out.println("User dose not exist");
            return new String[0];
        }

        return user.getFolders().toArray(new String[0]);
    }

    @Override
    public Contact getContact(String id) {
        try {
            return Data.getContact(id);
        } catch (IOException e) {
            System.out.println("Contact dosen't exist!");
            return null;
        }
    }

    public boolean createContact(String address, String name, String[] addresses) {
        User user;
        try {
            user = Data.getUser(address);
        } catch (IOException e) {
            System.out.println("User dose not exist");
            return false;
        }

        user.setCommand(new CreateContact(user, name, addresses));
        return user.execute();
    }

    public boolean AddContact(String address, String name, String[] addresses) {
        User user;
        Contact contact;
        try {
            user = Data.getUser(address);
        } catch (IOException e) {
            System.out.println("User dose not exist");
            return false;
        }
        try {
            contact = user.mainFolder().contact(name);
            if(contact == null)
                return false;
        } catch (IOException e) {
            System.out.println("Contact dose not exist");
            return false;
        }
        user.setCommand(new AddToContact(contact, addresses));
        return user.execute();
    }

    public boolean deleteContact(String address, String name) {
        User user;
        Contact contact;
        try {
            user = Data.getUser(address);
        } catch (IOException e) {
            System.out.println("User dose not exist");
            return false;
        }

        try {
            contact = user.mainFolder().contact(name);
            if(contact == null)
                return false;
        } catch (IOException e) {
            System.out.println("Contact dose not exist");
            return false;
        }

        user.setCommand(new DeleteContact(user, contact));
        return user.execute();
    }

    public boolean removeContact(String address, String name) {
        User user;
        Contact contact;
        try {
            user = Data.getUser(address);
        } catch (IOException e) {
            System.out.println("User dose not exist");
            return false;
        }

        try {
            contact = user.mainFolder().contact(name);
            if(contact == null)
                return false;
        } catch (IOException e) {
            System.out.println("Contact dose not exist");
            return false;
        }

        user.setCommand(new RemoveContact(contact, address));
        return user.execute();
    }
}