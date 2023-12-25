package com.csed26.speedmail.mail;

import java.io.IOException;
import java.util.ArrayList;

import com.csed26.speedmail.Data;
import com.csed26.speedmail.Server;
import com.csed26.speedmail.User;
import com.csed26.speedmail.critreria.CritreriaIF;

public class Mail {
    private String id;
    private ArrayList<String> to;
    private String from;
    private String subject;
    private ArrayList<CritreriaIF> types;
    private String body;
    private boolean isDraft;

    
    public void setIsDraft(boolean check) {
        this.isDraft = check;
    }

    public boolean getIsDraft() {
        return this.isDraft;
    }

    public Mail(String address){
        this.id = Server.generateId(10);
        this.to = new ArrayList<>();
        this.from = address;
        this.subject = "";
        this.types = new ArrayList<>();
        this.body = "";
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    public void addRecipient(String address) {
        this.to.add(address);
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
    public User getFrom() throws IOException {
        return Data.getUser(this.from);
    }

    public String getFromAddress() {
        return this.from;
    }
    public String getSubject() {
        return subject;
    }
    
    public String[] getTo() {
        return (String[]) this.to.toArray();
    }

    public CritreriaIF[] getTypes() {
        return (CritreriaIF[]) this.types.toArray();
    }

    public String getId() {
        return id;
    }

}
