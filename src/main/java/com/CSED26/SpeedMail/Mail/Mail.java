package com.csed26.speedmail.mail;

import java.io.IOException;
import java.util.ArrayList;

import com.csed26.speedmail.Data;
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

    public void send() throws IOException{
        
        Data.getUser(this.from).addToSend(this);
        for(String address : this.to){
                Data.getUser(address).recive(this);
        }

        if(this.isDraft)
            Data.deleteMail(this);

        this.isDraft = false;
    }

    public void save() throws IOException{
        Data.getUser(this.from).addToDraft(this);
        this.isDraft = true;
    }
}
