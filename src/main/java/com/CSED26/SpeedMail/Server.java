package com.csed26.speedmail;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import com.csed26.speedmail.commands.CreateFolder;
import com.csed26.speedmail.commands.DeleteEmail;
import com.csed26.speedmail.commands.DeleteFolder;
import com.csed26.speedmail.commands.Move;
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
        user.setCommand(new DeleteEmail(mail));
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
            folder = user.mainFolder().folder(oldName);
        } catch (IOException e) {
            return false;
        }
        user.setCommand(new RenameFolder(folder, newName));
        return user.execute();
    }

    @Override
    public boolean deleteFolder(String address, String folderName) {
        User user;
        Folder folder;
        try {
            user = Data.getUser(address);
            folder = user.mainFolder().folder(folderName);
        } catch (IOException e) {
            return false;
        }
        user.setCommand(new DeleteFolder(folder, folderName));
        return user.execute();

    }

    @Override
    public Mail[] serachMail(String address, String content) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serachMails'");
    }

    @Override
    public Mail[] serachContact(String address, String content) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serachContact'");
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

}
