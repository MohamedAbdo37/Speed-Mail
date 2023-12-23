package com.csed26.speedmail;

import java.io.IOException;
import java.util.ArrayList;

import com.csed26.speedmail.critreria.Critreria;
import com.csed26.speedmail.mail.Mail;
import com.csed26.speedmail.mail.Server;

public class Folder {

    private String id;
    private String folderName;
    private ArrayList<String> foldersIds;
    private ArrayList<String> mailsIds;
    // private Critreria type;

    private Folder(String folderName) {
        this.folderName = folderName;
        this.mailsIds = new ArrayList<>();
        this.foldersIds = new ArrayList<>();
        this.setId();
        try {
            Data.saveFolder(this);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Folder createNewAccount() {
        Folder account = new Folder("All");
        account.createFolder("Inbox");
        account.createFolder("Send");
        account.createFolder("Drafts");
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
     * 
     */
    public void createFolder(String folderName) {
        Folder folder = new Folder(folderName);
        this.foldersIds.add(folder.getId());
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
        
    }

    public void addToDraft(Mail mail) {
    }

    public void addToSend(Mail mail) {
    }

}
