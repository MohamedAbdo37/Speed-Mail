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
import org.springframework.web.bind.annotation.RequestBody;
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
        User user = null;
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
    public boolean draft(@RequestParam String[] to, @RequestParam String from, @RequestParam String messasge,
            @RequestParam String subject, @RequestParam String[] tag, @RequestParam int priority,
            @RequestParam String date, @RequestParam File[] attachments) {
        Server server = Server.getServer();
        Mail newMail = server.createMail(from, to, tag, attachments, subject, messasge, date, priority);
        boolean check = server.saveMail(from, newMail);
        return check;
    }

    @PostMapping("/send")
    public boolean send(@RequestParam String[] to, @RequestParam String from, @RequestParam String messasge,
            @RequestParam String subject, @RequestParam String[] tag, @RequestParam int priority,
            @RequestParam String date, @RequestParam File[] attachments) {
        Server server = Server.getServer();
        Mail newMail = server.createMail(from, to, tag, attachments, subject, messasge, date, priority);
        boolean check = server.sendMail(from, newMail);
        return check;
    }

    @PostMapping("/refresh")
    public Mail[] refresh(@RequestParam String address, @RequestParam String name) {
        Server server = Server.getServer();

        return server.getFolder(name, address);
    }

    @PostMapping("/gotofolder")
    public Mail[] gotoFolder(@RequestParam String address, @RequestParam String foldername) {
        Server server = Server.getServer();
        return server.getFolder(foldername, address);
    }

    @DeleteMapping("/deletefolder")
    public boolean deleteFolder(@RequestParam String address, @RequestParam String name) {
        Server server = Server.getServer();
        return server.deleteFolder(address, name);
    }

    @PostMapping("/renamefolder")
    public boolean renameFolder(@RequestParam String address, @RequestParam String oldname,
            @RequestParam String newname) {
        Server server = Server.getServer();
        return server.renameFolder(address, oldname, newname);

    }

    @PostMapping("/movefolder")
    public boolean moveFolder(@RequestParam String address, @RequestParam String[] ids, @RequestParam String from,
            @RequestParam String to) {
        Server server = Server.getServer();
        for (String id : ids) {
            if (!server.moveMail(address, from, to, server.getMail(id)))
                return false;
        }
        return true;
    }

    @PostMapping("/delete")
    public boolean delete(@RequestParam String address, @RequestParam String[] ids) {
        Server server = Server.getServer();
        for (String id : ids) {
            if (!server.deleteMail(address, server.getMail(id)))
                return false;
        }
        return true;
    }

    @PostMapping("/restore")
    public boolean restore(@RequestParam String address, @RequestParam String[] ids) {
        Server server = Server.getServer();
        for (String id : ids) {
            if (!server.restoreMail(address, server.getMail(id)))
                return false;
        }
        return true;
    }

    @PostMapping("/addfolder")
    public boolean addFolder(@RequestParam String address, @RequestParam String name) {
        Server server = Server.getServer();
        return server.createFolder(address, name);
    }

    @PostMapping("/filter")
    public Mail[] filter(@RequestParam String address, @RequestParam String filterby, @RequestParam String[] filer,
            @RequestParam String folder) {

        return new Mail[0];
    }

    /* ................... */@PostMapping("/search")
    public Mail[] search(@RequestParam String search, @RequestParam String type) {
        return null;
    }

}
