package com.csed26.speedmail;

import java.io.IOException;
import java.util.ArrayList;

import com.csed26.speedmail.mail.Mail;


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
