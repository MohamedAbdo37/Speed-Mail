package com.csed26.speedmail.commands;

import java.io.IOException;
import java.util.List;

import com.csed26.speedmail.Data;
import com.csed26.speedmail.Folder;
import com.csed26.speedmail.User;

public class RenameFolder implements Command {

    private Folder folder;
    private String name;
    private User user;
    public RenameFolder(Folder folder, String name, User user){
        this.folder = folder;
        this.name = name;
        this.user = user;
    }
    @Override
    public boolean execute() {
        List<String> folders = user.getFolders();
        folders.remove(folder.getFolderName());
        try {
            this.folder.setFolderName(this.name);
        } catch (IOException e) {
            System.out.println("Folder dose not exist!");
            return false;
        }
        folders.add(name);
        try {
            Data.saveUser(user);
        } catch (IOException e) {
            System.out.println("User dose not exist");
            return false;
        }
        return true;
    }
    
}
