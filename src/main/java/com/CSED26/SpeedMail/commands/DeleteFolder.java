package com.csed26.speedmail.commands;


import java.io.IOException;
import java.util.List;

import com.csed26.speedmail.Data;
import com.csed26.speedmail.Folder;
import com.csed26.speedmail.User;

public class DeleteFolder implements Command {

    private Folder folder;
    private String folderName;
    private User user;

    public DeleteFolder(Folder folder, String folderName, User user){
        this.folder = folder;
        this.folderName = folderName;
        this.user = user;
    }

    @Override
    public boolean execute() {
        try {
            this.folder.deleteFolder(folderName);
        } catch (IOException e) {
            System.out.println("Folder dose not exist");
            return false;
        }
        List<String> folders = user.getFolders();
        folders.remove(folderName);
        try {
            Data.saveUser(user);
        } catch (IOException e) {
            System.out.println("User dose not exist");
            return false;
        }

        return true;
    }
    
}
