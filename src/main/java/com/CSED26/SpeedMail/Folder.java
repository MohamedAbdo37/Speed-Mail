package com.csed26.speedmail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.CSED26.SpeedMail.Critreria.AndFilter;
import com.CSED26.SpeedMail.Critreria.Filter;
import com.CSED26.SpeedMail.Critreria.FromFilter;
import com.CSED26.SpeedMail.Critreria.OrFilter;
import com.CSED26.SpeedMail.Critreria.ToFilter;
import com.CSED26.SpeedMail.Critreria.TypeFilter;
import com.CSED26.SpeedMail.Mail.Mail;
import com.CSED26.SpeedMail.Server;

public class Folder {

    private String id;
    private String folderName;
    private ArrayList<String> foldersIds;
    private ArrayList<String> mailsIds;
    private boolean main = false;

    private static String inBox = "Inbox";
    private static String send = "Send";
    private static String drafts = "Drafts";
    private static String trash = "Trash";
    // private Critreria type;

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    private void setMain() {
        this.main = true;
    }

    private Folder(String folderName) throws IOException {
        this.folderName = folderName;
        this.mailsIds = new ArrayList<>();
        this.foldersIds = new ArrayList<>();
        this.setId();
        Data.saveFolder(this);
    }

    private Folder(String folderName, String address) throws IOException {
        this.folderName = folderName;
        this.mailsIds = new ArrayList<>();
        this.foldersIds = new ArrayList<>();
        this.setId(address);
        this.setMain();
        Data.saveFolder(this);
    }

    public static Folder createNewAccount(String address) throws IOException {
        Folder account = new Folder("main", address);
        account.createFolder(inBox).setMain();
        account.createFolder(send).setMain();
        account.createFolder(drafts).setMain();
        account.createFolder(trash).setMain();
        try {
            Folder inbox = account.getFolder("Inbox");
            inbox.createFolder("Social");
            inbox.createFolder("Offers");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    /**
     * @param folderName
     * @throws IOException
     * 
     */
    public Folder createFolder(String folderName) throws IOException {
        Folder folder = new Folder(folderName);
        this.foldersIds.add(folder.getId());
        return folder;
    }

    /**
     * @return Folder's name
     */
    public String getFolderName() {
        return folderName;
    }

    /**
     * @param folderName
     * @return specific folder with such name
     */
    private Folder getFolder(String folderName) {

        for (Folder folder : this.getFolders())
            if (folder.getFolderName().equals(folderName))
                return folder;
        return null;
    }

    private void setId() {
        this.id = Server.generateId(10);
    }

    private void setId(String address) {
        this.id = address;
    }

    public String getId() {
        return id;
    }

    /**
     * @return Array of all folders in this folder
     */
    public Folder[] getFolders() {
        Folder[] folders = new Folder[this.foldersIds.size()];
        for (int i = 0; i < folders.length; i++) {
            try {
                folders[i] = Data.getFolder(this.foldersIds.get(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return folders;
    }

    /**
     * @return Array of all mails' ids in this folder.
     */
    public String[] getMails() {
        return (String[]) this.mailsIds.toArray();
    }

    public String[] filterMailsByTo(String toString) {
        Filter toFilter = new ToFilter(toString);
        List<Mail> filterdMails = toFilter.apply(getMailsAsMails());
        List<String> filterdMailsIds = new ArrayList<>();
        for (Mail mail : filterdMails) {
            filterdMailsIds.add(mail.getId());
        }
        return filterdMailsIds.toArray(new String[0]);
    }

    public String[] filterMailsByFrom(String fromString) {
        Filter fromFilter = new FromFilter(fromString);
        List<Mail> filterdMails = fromFilter.apply(getMailsAsMails());
        List<String> filterdMailsIds = new ArrayList<>();
        for (Mail mail : filterdMails) {
            filterdMailsIds.add(mail.getId());
        }
        return filterdMailsIds.toArray(new String[0]);
    }

    public String[] filterMailsByType(String typeString) {
        Filter typeFilter = new TypeFilter(typeString);
        List<Mail> filterdMails = typeFilter.apply(getMailsAsMails());
        List<String> filterdMailsIds = new ArrayList<>();
        for (Mail mail : filterdMails) {
            filterdMailsIds.add(mail.getId());
        }
        return filterdMailsIds.toArray(new String[0]);
    }

    public String[] andFilterMails(String firstCri, String firstCriString, String secondCri, String secondCriString) {
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
        List<Mail> filterdMails = andFilter.apply(getMailsAsMails());
        List<String> filterdMailsIds = new ArrayList<>();
        for (Mail mail : filterdMails) {
            filterdMailsIds.add(mail.getId());
        }
        return filterdMailsIds.toArray(new String[0]);
    }

    public String[] orFilterMails(String firstCri, String firstCriString, String secondCri, String secondCriString) {
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
        List<Mail> filterdMails = orFilter.apply(getMailsAsMails());
        List<String> filterdMailsIds = new ArrayList<>();
        for (Mail mail : filterdMails) {
            filterdMailsIds.add(mail.getId());
        }
        return filterdMailsIds.toArray(new String[0]);
    }

    public List<Mail> getMailsAsMails() {
        List<Mail> mails = new ArrayList<>();
        for (String id : this.mailsIds) {
            try {
                mails.add(Data.getMail(id));
            } catch (Exception IOException) {
                System.out.println("Error in getMailsAsMails:Folder.java");
            }
        }
        return mails;
    }

    public void addToIndex(Mail mail) {
        try {
            this.getFolder(inBox).addMail(mail);
        } catch (Exception e) {
            e.getStackTrace();
        }

        this.placeMail(mail);
    }

    private void placeMail(Mail mail) {
    }

    public void addToTrash(Mail mail) {
        try {
            this.getFolder(trash).addMail(mail);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void addToDraft(Mail mail) {
        try {
            this.getFolder(drafts).addMail(mail);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void addToSend(Mail mail) {
        try {
            this.getFolder(send).addMail(mail);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void addMail(Mail mail) {
        if (mail == null)
            throw new NullPointerException("null mail");
        this.mailsIds.add(mail.getId());
    }

    public void removeFromDraft(Mail mail) {
        this.getFolder(drafts).removeMail(mail.getId());
    }

    private void removeMail(String id) {
        this.mailsIds.remove(id);
    }

    public void removeFromSend(Mail mail) {
        this.getFolder(drafts).removeMail(mail.getId());
    }

    public void deleteFolder(String name) {
        this.getFolder(name).delete();
    }

    private void delete() {
        Data.deleteFolder(this);
    }

    public void removeFromTrash(Mail mail) {
        this.getFolder(trash).removeMail(mail.getId());
    }

    public void removeFrom(String source, Mail mail) {
        this.getFolder(source).removeMail(mail.getId());
    }

    public void addTo(String dest, Mail mail) {
        this.getFolder(dest).addMail(mail);
    }

}
