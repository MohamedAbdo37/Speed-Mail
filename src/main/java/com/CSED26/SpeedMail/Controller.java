package com.csed26.speedmail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csed26.speedmail.mail.Mail;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
public class Controller {
    public User user = null;

    @PostMapping("/login")
    public User logIN(@RequestParam String address, @RequestParam String password) {
        try {
            user = Server.getServer().logIn(address, password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @PostMapping("/register")
    public User register(@RequestParam String name, @RequestParam String address, @RequestParam String password) {
        try {
            Server server = Server.getServer();
            user = server.register(name, address, password);
        } catch (IOException e) {
            e.getStackTrace();
            System.out.println("email already exist");
            return null;
        }
        return user;
    }

    @PostMapping("/draft")
    public boolean draft(@RequestParam String to, @RequestParam String from, @RequestParam String messasge,
            @RequestParam String subject, @RequestParam String tag, @RequestParam int priority,
            @RequestParam String date, @RequestParam File attachments) {
        Server server = Server.getServer();
        Mail newMail = server.createMail(messasge, null, null, null, subject, tag, date, priority);
        boolean check = server.saveMail(from, newMail);
        return check;
    }

    @PostMapping("/send")
    public boolean send(@RequestParam String to, @RequestParam String from, @RequestParam String messasge,
            @RequestParam String subject, @RequestParam String tag, @RequestParam int priority,
            @RequestParam String date, @RequestParam File attachments) {
        Server server = Server.getServer();
        Mail newMail = server.createMail(messasge, null, null, null, subject, tag, date, priority);
        boolean check = server.sendMail(from, newMail);
        return check;
    }

    @PostMapping("/refresh")
    public Mail[] refresh(@RequestParam String name) throws IOException {
        Server server = Server.getServer();
        List<Mail> mails = new ArrayList<>();
        for (String mailId : user.mainFolder().folder(name).getMailsIds()) {
            mails.add(server.getMail(mailId));
        }
        return mails.toArray(new Mail[0]);
    }

    @PostMapping("/gotofolder")
    public Mail[] gotoFolder(@RequestParam String foldername) throws IOException {
        Server server = Server.getServer();
        List<Mail> mails = new ArrayList<>();
        for (String mailId : user.mainFolder().folder(foldername).getMailsIds()) {
            mails.add(server.getMail(mailId));
        }
        return mails.toArray(new Mail[0]);
    }

    @DeleteMapping("/deletefolder")
    public void deleteFolder(@RequestParam String name) throws IOException {
        user.mainFolder().deleteFolder(name);
    }

    @PostMapping("/renamefolder")
    public void renameFolder(@RequestParam String oldname, @RequestParam String newname) throws IOException {
        List<Mail> folderMails = new ArrayList<>();
        user.mainFolder().folder(oldname).setFolderName(newname);
    }

    /* ................... */@PostMapping("/movefolder")
    public Mail[] moveFolder(@RequestParam String[] ids, @RequestParam String from, @RequestParam String to) {
        List<Mail> folderMails = new ArrayList<>();

        return folderMails.toArray(new Mail[0]);
    }

    /* ................... */@PostMapping("/Trash")
    public void trash(@RequestParam String address, @RequestParam String name) {
        Server server = Server.getServer();

        server.deleteMail(address, na);
    }

    /* ................... */@PostMapping("/delete")
    public Mail[] delete(@RequestParam String ID) {
        List<Mail> folderMails = new ArrayList<>();

        return folderMails.toArray(new Mail[0]);
    }

    /* ................... */@PostMapping("/restore")
    public Mail[] restore(@RequestParam String ID) {
        List<Mail> folderMails = new ArrayList<>();

        return folderMails.toArray(new Mail[0]);
    }

    @PostMapping("/addfolder")
    public void addFolder(@RequestParam String name) throws IOException {
        user.addFolder(name);
    }

    @PostMapping("/filter")
    public Mail[] filter(@RequestParam String filterby, @RequestParam String filter, @RequestParam Mail[] mails) {
        return Data.filterBy(filterby, filter, mails);
    }

    /* ................... */@PostMapping("/search")
    public Mail[] search(@RequestParam String search, @RequestParam String type) {
        return null;
    }

    @PostMapping("/gotoinbox")
    public Mail[] gotoInbox(@RequestParam String foldername) throws IOException {
        Server server = Server.getServer();
        List<Mail> mails = new ArrayList<>();
        for (String mailId : user.mainFolder().folder(foldername).getMailsIds()) {
            mails.add(server.getMail(mailId));
        }
        return mails.toArray(new Mail[0]);
    }

}
