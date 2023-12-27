package com.csed26.speedmail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csed26.speedmail.mail.Mail;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
public class Controller {

    @PostMapping("/login")
    public User logIN(@RequestParam String address, @RequestParam String password) {
        User user = null;
        try {
            user = Server.getServer().logIn(address, password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @PostMapping("/register")
    public User register(@RequestParam String name, @RequestParam String address, @RequestParam String password) {
        User user;
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
    public String draft(@RequestParam String to, @RequestParam String from, @RequestParam String messasge,
            @RequestParam String subject, @RequestParam String tag, @RequestParam int priority,
            @RequestParam String date, @RequestParam File attachments) {

        return "saved in draft";
    }

    @PostMapping("/send")
    public String send(@RequestParam String to, @RequestParam String from, @RequestParam String messasge,
            @RequestParam String subject, @RequestParam String tag, @RequestParam int priority,
            @RequestParam String date, @RequestParam File attachments) {

        return "sent successfully";
    }

    @PostMapping("/refresh")
    public Mail[] send(@RequestParam String name) {
        List<Mail> folderMails = new ArrayList<>();

        return folderMails.toArray(new Mail[0]);
    }

    @PostMapping("/gotofolder")
    public Mail[] gotoFolder(@RequestParam String foldername) {
        List<Mail> folderMails = new ArrayList<>();

        return folderMails.toArray(new Mail[0]);
    }

    @PostMapping("/deletefolder")
    public Mail[] deleteFolder(@RequestParam String name) {
        List<Mail> folderMails = new ArrayList<>();

        return folderMails.toArray(new Mail[0]);
    }

    @PostMapping("/renamefolder")
    public Mail[] renameFolder(@RequestParam String oldname, @RequestParam String newname) {
        List<Mail> folderMails = new ArrayList<>();

        return folderMails.toArray(new Mail[0]);
    }

    @PostMapping("/movefolder")
    public Mail[] moveFolder(@RequestParam String ids, @RequestParam String from, @RequestParam String to) {
        List<Mail> folderMails = new ArrayList<>();

        return folderMails.toArray(new Mail[0]);
    }

    @PostMapping("/Trash")
    public Mail[] trash(@RequestParam String name) {
        List<Mail> folderMails = new ArrayList<>();

        return folderMails.toArray(new Mail[0]);
    }

    @PostMapping("/delete")
    public Mail[] delete(@RequestParam String ID) {
        List<Mail> folderMails = new ArrayList<>();

        return folderMails.toArray(new Mail[0]);
    }

    @PostMapping("/restore")
    public Mail[] restore(@RequestParam String ID) {
        List<Mail> folderMails = new ArrayList<>();

        return folderMails.toArray(new Mail[0]);
    }

    @PostMapping("/addfolder")
    public Mail[] addFolder(@RequestParam String name) {
        List<Mail> folderMails = new ArrayList<>();

        return folderMails.toArray(new Mail[0]);
    }

    @PostMapping("/filter")
    public Mail[] filter(@RequestParam String filterby, @RequestParam String filter, @RequestParam Mail[] mails) {
        return Data.filterBy(filterby, filter, mails);
    }

    @PostMapping("/search")
    public Mail[] search(@RequestParam String search, @RequestParam String type) {
        return null;
    }

    @PostMapping("/gotoinbox")
    public Mail[] gotoInbox(@RequestParam String foldername) {
        List<Mail> folderMails = new ArrayList<>();

        return folderMails.toArray(new Mail[0]);
    }

}
