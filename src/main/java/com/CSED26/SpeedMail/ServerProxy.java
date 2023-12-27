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
    public Mail[] serachBody(String address, String content) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serachBody'");
    }

    @Override
    public Mail[] serachC(String address, String content) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serachC'");
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

}
