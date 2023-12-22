package com.CSED26.SpeedMail;

import java.util.ArrayList;

import com.CSED26.SpeedMail.Critreria.Critreria;

public class Folder {

    private String folderName;
    private ArrayList<Folder> folders;
    private ArrayList<String> mails;
    // private Critreria type;

    private Folder(String folderName) {
        this.folderName = folderName;
        this.mails = new ArrayList<>();
        this.folders = new ArrayList<>();
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
        return account;
    }

    /**
     * @param folderName
     * 
     */
    public void createFolder(String folderName) {
        this.folders.add(new Folder(folderName));
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
    private Folder getFolder(String folderName){
        for (Folder folder : this.folders)
                if (folder.getFolderName().equals(folderName))
                    return folder;  
        return null;
    }


    /**
     * @return Array of all folders in this folder
     */
    public Folder[] getFolders() {
        return (Folder[]) this.folders.toArray();
    }

    /**
     * @return Array of all mails' ids in this folder.
     */
    public String[] getMails() {
        return (String[]) this.mails.toArray();
    }

}
