package com.CSED26.SpeedMail.Mail;

import java.io.IOException;
import java.util.ArrayList;

import com.CSED26.SpeedMail.Data;
import com.CSED26.SpeedMail.Types;
import com.CSED26.SpeedMail.User;
import com.CSED26.SpeedMail.Critreria.ToFilter;

public class Mail {
    private String id;
    private ArrayList<String> to;
    private String from;
    private String subject;
    private Types types;
    private String body;
    private boolean isDraft;

    public void setIsDraft(boolean check) {
        this.isDraft = check;
    }

    public boolean getIsDraft() {
        return this.isDraft;
    }

    public Mail(String address) {
        this.id = Server.generateId(10);
        this.to = new ArrayList<>();
        this.from = address;
        this.subject = "";
        this.types = new Types();
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

    public void addType(String type) {
        this.types.addType(type);
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

    public Types getTypes() {
        return this.types;
    }

    public String getId() {
        return id;
    }

}
