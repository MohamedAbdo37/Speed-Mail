package com.csed26.speedmail.mail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.csed26.speedmail.Data;
import com.csed26.speedmail.Types;
import com.csed26.speedmail.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.csed26.speedmail.Server;

public class Mail {
    private String id;
    private ArrayList<String> to;
    private String from;
    private String subject;
    private Types types;
    private String body;
    private File[] attachments;
    private boolean isDraft;
    private String date;
    private int priority;


    public Mail(@JsonProperty("id") String id, @JsonProperty("from") String from,
            @JsonProperty("to") ArrayList<String> to, @JsonProperty("body") String body,
            @JsonProperty("subject") String subject, @JsonProperty("isDraft") boolean isDraft,
            @JsonProperty("attachments") File[] attachments, @JsonProperty("types") Types types,
            @JsonProperty("date") String date, @JsonProperty("priority") int priority) {

        this.to = to;
        this.types = types;
        this.attachments = attachments;
        this.body = body;
        this.from = from;
        this.id = id;
        this.subject = subject;
        this.isDraft = isDraft;
        this.date = date;
        this.priority = priority;
    }

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

    public void addAttachment(File[] file) {
        this.attachments = file;
    }

    public String getBody() {
        return body;
    }

    public User fromUser() throws IOException {
        return Data.getUser(this.from);
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public String[] to() {
        return (String[]) this.to.toArray();
    }

    public ArrayList<String> getTo() {
        return to;
    }

    public Types getTypes() {
        return this.types;
    }

    public String getId() {
        return id;
    }

    public File[] getAttachment() {
        return this.attachments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
