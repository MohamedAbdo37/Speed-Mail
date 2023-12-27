package com.csed26.speedmail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.csed26.speedmail.mail.Mail;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.csed26.speedmail.critreria.AndFilter;
import com.csed26.speedmail.critreria.Filter;
import com.csed26.speedmail.critreria.FromFilter;
import com.csed26.speedmail.critreria.OrFilter;
import com.csed26.speedmail.critreria.ToFilter;
import com.csed26.speedmail.critreria.TypeFilter;

public class Folder {

    private String id;
    private String folderName;
    private List<String> foldersIds;
    private List<String> mailsIds;
    private Boolean main = false;

    private static String inBox = "Inbox";
    private static String send = "Send";
    private static String drafts = "Drafts";
    private static String trash = "Trash";
    // private Critreria type;

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public Folder(@JsonProperty("id") String id, @JsonProperty("folderName") String folderName,
            @JsonProperty("foldersIds") List<String> foldersIds, @JsonProperty("mailsIds") List<String> mailsIds,
            @JsonProperty("main") boolean main) {
        this.id = id;
        this.folderName = folderName;
        this.foldersIds = foldersIds;
        this.mailsIds = mailsIds;
        this.main = main;
    }

    private void setMain() throws IOException {
        this.main = true;
        Data.saveFolder(this);
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
        this.main = true;
        Data.saveFolder(this);
    }

    public static Folder createNewAccount(String address) throws IOException {
        Folder account = new Folder("main", address);
        User user = Data.getUser(address);

        account.createFolder(inBox).setMain();
        account.createFolder(send).setMain();
        account.createFolder(drafts).setMain();
        account.createFolder(trash).setMain();
        try {
            Folder inbox = account.folder("Inbox");
            inbox.createFolder("Social");
            user.addFolder("Social");
            inbox.createFolder("Offers");
            user.addFolder("Offers");
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
        Data.saveFolder(this);
        return folder;
    }

    public List<String> getFoldersIds() {
        return foldersIds;
    }

    public List<String> getMailsIds() {
        return mailsIds;
    }

    public Boolean getMain() {
        return main;
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

    public Folder folder(String folderName) {

        for (Folder folder : this.folders())
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
    public Folder[] folders() {
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
    public String[] Mails() {
        return (String[]) this.mailsIds.toArray();
    }

    public void addToIndex(Mail mail) {
        try {
            this.folder(inBox).addMail(mail);
        } catch (Exception e) {
            e.getStackTrace();
        }

        this.placeMail(mail);
    }

    private void placeMail(Mail mail) {
    }

    public void addToTrash(Mail mail) {
        try {
            this.folder(trash).addMail(mail);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void addToDraft(Mail mail) {
        try {
            this.folder(drafts).addMail(mail);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void addToSend(Mail mail) {
        try {
            this.folder(send).addMail(mail);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void addMail(Mail mail) throws Exception {
        if (mail == null)
            throw new Exception("null mail");
        this.mailsIds.add(mail.getId());
    }

    public void removeFromDraft(Mail mail) {
        this.folder(drafts).removeMail(mail.getId());
    }

    private void removeMail(String id) {
        this.mailsIds.remove(id);
    }

    public void removeFromSend(Mail mail) {
        this.folder(drafts).removeMail(mail.getId());
    }

    public void deleteFolder(String name) {
        this.folder(name).delete();
    }

    private void delete() {
        Data.deleteFolder(this);
    }

    public void removeFromTrash(Mail mail) {
        this.folder(trash).removeMail(mail.getId());
    }

    public void removeFrom(String source, Mail mail) {
        this.folder(source).removeMail(mail.getId());
    }

    public void addTo(String dest, Mail mail) throws Exception {
        this.folder(dest).addMail(mail);
    }

    // setters

}
