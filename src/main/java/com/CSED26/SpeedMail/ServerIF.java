package com.csed26.speedmail;

import java.io.File;

import com.csed26.speedmail.mail.Mail;

public interface ServerIF {
        public Mail getMail(String id);

        public Contact getContact(String id);

        public Mail[] getFolder(String folder, String address);

        public Contact[] getContacts(String address);

        public Mail createMail(String sender, String[] to, String[] types, File[] files, String subject, String body,
                        String date, int priority);

        public Mail[] serachMail(String address, String content, String type);

        public Contact[] serachContact(String address, String content, String type);

        // Mail Manipulation
        public boolean saveMail(String address, Mail mail);

        public boolean sendMail(String address, Mail mail);

        public boolean deleteMail(String address, Mail mail);

        public boolean restoreMail(String address, Mail mail);

        public boolean moveMail(String address, String source, String dest, Mail mail);

        // Folder Manipulation
        public boolean createFolder(String address, String folderName);

        public boolean renameFolder(String address, String oldName, String newName);

        public boolean deleteFolder(String address, String folderName);

        // filter maniputlaion
        public Mail[] filterMailsByTo(String toString, Mail[] mails);

        public Mail[] filterMailsByFrom(String fromString, Mail[] mails);

        public Mail[] filterMailsByType(String typeString, Mail[] mails);

        public Mail[] filterBy(String filterBy, String filterString, Mail[] mails);

        public Mail[] andFilterMails(String firstCri, String firstCriString, String secondCri,
                        String secondCriString, Mail[] mails);

        public Mail[] orFilterMails(String firstCri, String firstCriString, String secondCri,
                        String secondCriString, Mail[] mails);

}
