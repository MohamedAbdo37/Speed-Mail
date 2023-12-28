package com.csed26.speedmail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.csed26.speedmail.mail.Mail;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Folder {

    private String id;
    private String folderName;
    private List<String> foldersIds;
    private List<String> elementsId;
    private Boolean main = false;

    public static String inBox = "Inbox";
    public static String send = "Send";
    public static String drafts = "Drafts";
    public static String trash = "Trash";
    public static String contacts = "Contacts";

    public void setFolderName(String folderName) throws IOException {
        this.folderName = folderName;
        Data.saveFolder(this);
    }

    public Folder(@JsonProperty("id") String id, @JsonProperty("folderName") String folderName,
            @JsonProperty("foldersIds") List<String> foldersIds, @JsonProperty("elementsId") List<String> elementsId,
            @JsonProperty("main") boolean main) {
        this.id = id;
        this.folderName = folderName;
        this.foldersIds = foldersIds;
        this.elementsId = elementsId;
        this.main = main;
    }

    private void setMain() throws IOException {
        this.main = true;
        Data.saveFolder(this);
    }

    private Folder(String folderName) throws IOException {
        this.folderName = folderName;
        this.elementsId = new ArrayList<>();
        this.foldersIds = new ArrayList<>();
        this.setId();
        Data.saveFolder(this);
    }

    private Folder(String folderName, String address) throws IOException {
        this.folderName = folderName;
        this.elementsId = new ArrayList<>();
        this.foldersIds = new ArrayList<>();
        this.setId(address);
        this.main = true;
        Data.saveFolder(this);
    }

    public static Folder createNewAccount(String address) throws IOException {
        Folder account = new Folder("main", address);

        account.createFolder(inBox).setMain();
        account.createFolder(send).setMain();
        account.createFolder(drafts).setMain();
        account.createFolder(trash).setMain();
        account.createFolder(contacts).setMain();

        try {
            Folder inbox = account.folder(inBox);
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
        Data.saveFolder(this);
        return folder;
    }

    public List<String> getFoldersIds() {
        return foldersIds;
    }

    public List<String> getElementsId() {
        return elementsId;
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
        return this.elementsId.toArray(new String[0]);
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
        this.elementsId.add(mail.getId());
        Data.saveFolder(this);
    }

    public void removeFromDraft(Mail mail) throws IOException {
        this.folder(drafts).removeMail(mail.getId());
    }

    private void removeMail(String id) throws IOException {
        this.elementsId.remove(id);
        Data.saveFolder(this);
    }

    public void removeFromSend(Mail mail) throws IOException {
        this.folder(drafts).removeMail(mail.getId());
    }

    public void deleteFolder(String name) throws IOException {
        this.folder(name).delete();
        Data.saveFolder(this);
    }

    private void delete() {
        Data.deleteFolder(this);
    }

    public void removeFromTrash(Mail mail) throws IOException {
        this.folder(trash).removeMail(mail.getId());
    }

    public void removeFrom(String source, Mail mail) throws IOException {
        this.folder(source).removeMail(mail.getId());
    }

    public void addTo(String dest, Mail mail) throws Exception {
        this.folder(dest).addMail(mail);
    }

    public void addContact(Contact contact){
        this.folder(contacts).elementsId.add(contact.getId());
    }
    // setters

    public void removeFromInbox(Mail mail) throws IOException {
        this.folder(inBox).removeMail(mail.getId());
        for (Folder folder : this.folder(inBox).folders()) {
            folder.removeMail(mail.getId());
        }
    }

}
