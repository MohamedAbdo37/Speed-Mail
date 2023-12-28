package com.csed26.speedmail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.csed26.speedmail.mail.Mail;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
public class Controller {

    @GetMapping("/login")
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

    @GetMapping("/send")
    public boolean send(@ModelAttribute String[] to, @RequestParam String from, @RequestParam String messasge,
            @RequestParam String subject, @ModelAttribute String[] tag, @RequestParam int priority,
            @RequestParam String date, @ModelAttribute File[] attachments) {
        Server server = Server.getServer();
        File[] attachmentsArray = new File[1];
        // attachmentsArray[0] = attachments;
        System.out.println(Arrays.toString(to));
        System.out.println(from);
        System.out.println(messasge);
        System.out.println(subject);
        System.out.println(Arrays.toString(tag));
        System.out.println(priority);
        System.out.println(date);
        Mail newMail = server.createMail(from, to, tag, attachments, subject, messasge, date, priority);
        boolean check = server.sendMail(from, newMail);
        return check;
    }

    @PutMapping("/refresh")
    public Mail[] refresh(@RequestParam String address, @RequestParam String name) {
        Server server = Server.getServer();

        return server.getFolder(name, address);
    }

    @GetMapping("/gotofolder")
    public Mail[] gotoFolder(@RequestParam String address, @RequestParam String foldername) {
        Server server = Server.getServer();
        return server.getFolder(foldername, address);
    }

    @DeleteMapping("/deletefolder")
    public boolean deleteFolder(@RequestParam String address, @RequestParam String name) {
        Server server = Server.getServer();
        return server.deleteFolder(address, name);
    }

    @PutMapping("/renamefolder")
    public boolean renameFolder(@RequestParam String address, @RequestParam String oldname,
            @RequestParam String newname) {
        Server server = Server.getServer();
        return server.renameFolder(address, oldname, newname);

    }

    @PutMapping("/movefolder")
    public boolean moveFolder(@RequestParam String address, @RequestParam String[] ids, @RequestParam String from,
            @RequestParam String to) {
        Server server = Server.getServer();
        for (String id : ids) {
            if (!server.moveMail(address, from, to, server.getMail(id)))
                return false;
        }
        return true;
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam String address, @RequestParam String[] ids) {
        Server server = Server.getServer();
        for (String id : ids) {
            if (!server.deleteMail(address, server.getMail(id)))
                return false;
        }
        return true;
    }

    @PutMapping("/restore")
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
    public Mail[] filter(@RequestParam String address, @RequestParam String filterby, @RequestParam String filer,
            @RequestParam String folder) {
        Server server = Server.getServer();
        return server.filterBy(filterby, filer, server.getFolder(folder, address));
    }

    @PostMapping("/searchMail")
    public Mail[] searchMail(@RequestParam String address, @RequestParam String search, @RequestParam String type) {
        Server server = Server.getServer();
        return server.serachMail(address, search, type);
    }

    @PostMapping("/searchContacts")
    public Contact[] searchContact(@RequestParam String address, @RequestParam String search,
            @RequestParam String type) {
        Server server = Server.getServer();
        return server.serachContact(address, search, type);
    }

    @GetMapping("/foldes")
    public String[] foldesName(@RequestParam String address) {
        Server server = Server.getServer();
        return server.userFolders(address);
    }

    @GetMapping("/mail")
    public Mail mail(@RequestParam String id) {
        Server server = Server.getServer();
        return server.getMail(id);
    }

    @PostMapping("/createContact")
    public boolean createContact(@RequestBody String address, @RequestBody String name,
            @RequestBody String[] addresses) {
        Server server = Server.getServer();
        return server.createContact(address, name, addresses);
    }

    @PostMapping("/deleteContact")
    public boolean deleteContact(@RequestBody String address, @RequestBody String name) {
        Server server = Server.getServer();
        return server.deleteContact(address, name);
    }

}
