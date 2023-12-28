package com.csed26.speedmail.mail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.csed26.speedmail.Data;
import com.csed26.speedmail.Types;
import com.csed26.speedmail.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.csed26.speedmail.Server;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Mail {
    private String id;
    private ArrayList<String> to;
    private String from;
    private String subject;
    private List<String> types;
    private String body;
    private File[] attachments;
    private boolean isDraft = true;
    private String date;
    private int priority;

    public Mail(@JsonProperty("id") String id, @JsonProperty("from") String from,
            @JsonProperty("to") ArrayList<String> to, @JsonProperty("body") String body,
            @JsonProperty("subject") String subject, @JsonProperty("isDraft") boolean isDraft,
            @JsonProperty("attachments") File[] attachments, @JsonProperty("types") List<String> types,
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

    public void addType(String type) {
        this.types.add(type);
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
        return this.to.toArray(new String[0]);
    }

    public List<String> getTo() {
        return to;
    }

    public List<String> getTypes() {
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

    // search
    public boolean subjectHas(String content) {
        if (this.subject.contains(content))
            return true;
        return false;
    }

    public boolean bodyHas(String content) {
        if (this.body.contains(content))
            return true;
        return false;
    }

    public boolean fromHas(String content) {
        if (this.from.contains(content))
            return true;
        return false;
    }

    public boolean toHas(String content) {
        for (String address : this.to)
            if (address.contains(content))
                return true;

        return false;
    }

    public boolean dateHas(String content) {
        if (this.date.contains(content))
            return true;
        return false;
    }

    public boolean searchForType(String type) {
        for (String temp : this.types) {
            if (temp.equals(type))
                return true;
        }
        return false;
    }

}
