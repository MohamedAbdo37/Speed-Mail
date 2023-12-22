package com.CSED26.SpeedMail.Mail;

import java.util.ArrayList;

import com.CSED26.SpeedMail.User;
import com.CSED26.SpeedMail.Critreria.CritreriaIF;

public class Mail {
    private String id;
    private ArrayList<User> to;
    private User from;
    private String subject;
    private ArrayList<CritreriaIF> types;
    private String body;

    public Mail(User sender){
        // this.id = id;
        this.to = new ArrayList<>();
        this.from = sender;
        this.subject = "";
        this.types = new ArrayList<>();
        this.body = "";
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    public void addRecipient(User recipient) {
        this.to.add(recipient);
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void addType(CritreriaIF type) {
        this.types.add(type);
    }

    public String getBody() {
        return body;
    }
    public User getFrom() {
        return from;
    }
    public String getSubject() {
        return subject;
    }
    
    public User[] getTo() {
        return (User[]) this.to.toArray();
    }

    public CritreriaIF[] getTypes() {
        return (CritreriaIF[]) this.types.toArray();
    }

    public String getId() {
        return id;
    }


}
