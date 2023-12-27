package com.csed26.speedmail;

import java.io.File;

import com.csed26.speedmail.mail.Mail;

public interface ServerIF {
    public Mail getMail(String id);

    public Mail[] getFolder(String folder, String address);

    public Mail createMail(String sender, String[] to, String[] types, File[] files, String subject, String body,
            String date, int priority);
    
    public Mail[] serachBody(String address,String content);

    public Mail[] serachC(String address,String content);
    // Mail Manipulation
    public boolean saveMail(String address, Mail mail);

    public boolean sendMail(String address, Mail mail);

    public boolean deleteMail(String address, Mail mail);

    public boolean restoreMail(String address, Mail mail);

    public boolean moveMail(String address, String source, String dest, Mail mail);

    // Folder Manipulation
    public boolean createFolder (String address, String folderName);

    public boolean renameFolder (String address, String oldName, String newName);
    
    public boolean deleteFolder (String address, String folderName);
}
