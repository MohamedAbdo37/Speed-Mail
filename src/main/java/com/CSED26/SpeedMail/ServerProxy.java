package com.csed26.speedmail;

import java.io.File;

import com.csed26.speedmail.mail.Mail;

public class ServerProxy implements ServerIF {

    @Override
    public Mail getMail(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMail'");
    }

    @Override
    public Mail[] getFolder(String folder, String address) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFolder'");
    }

    @Override
    public Mail createMail(String sender, String[] to, String[] types, File[] files, String subject, String body,
            String date, int priority) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createMail'");
    }

    @Override
    public boolean saveMail(String address, Mail mail) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveMail'");
    }

    @Override
    public boolean sendMail(String address, Mail mail) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendMail'");
    }

    @Override
    public boolean deleteMail(String address, Mail mail) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMail'");
    }

    @Override
    public boolean restoreMail(String address, Mail mail) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'restoreMail'");
    }

    @Override
    public boolean moveMail(String address, String source, String dest, Mail mail) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveMail'");
    }

    @Override
    public boolean createFolder(String address, String folderName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createFolder'");
    }

    @Override
    public boolean renameFolder(String address, String oldName, String newName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renameFolder'");
    }

    @Override
    public boolean deleteFolder(String address, String folderName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFolder'");
    }

    @Override
    public Mail[] serachMail(String address, String content, String type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serachMail'");
    }


    @Override
    public Mail[] filterMailsByTo(String toString, Mail[] mails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filterMailsByTo'");
    }

    @Override
    public Mail[] filterMailsByFrom(String fromString, Mail[] mails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filterMailsByFrom'");
    }

    @Override
    public Mail[] filterMailsByType(String typeString, Mail[] mails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filterMailsByType'");
    }

    @Override
    public Mail[] filterBy(String filterBy, String filterString, Mail[] mails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filterBy'");
    }

    @Override
    public Mail[] andFilterMails(String firstCri, String firstCriString, String secondCri, String secondCriString,
            Mail[] mails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'andFilterMails'");
    }

    @Override
    public Mail[] orFilterMails(String firstCri, String firstCriString, String secondCri, String secondCriString,
            Mail[] mails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orFilterMails'");
    }

    @Override
    public Contact[] getContacts(String address) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getContacts'");
    }

    @Override
    public Contact getContact(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getContact'");
    }

    @Override
    public Contact[] serachContact(String address, String content, String type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serachContact'");
    }


}
