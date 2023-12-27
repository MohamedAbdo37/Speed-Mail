package com.csed26.speedmail.commands;

import java.io.IOException;

import com.csed26.speedmail.Folder;
import com.csed26.speedmail.User;

public class DeleteFolder implements Command {

    private Folder folder;
    private String folderName;

    public DeleteFolder(User user, String folderName) throws IOException{
        this.folder = user.mainFolder();
        this.folderName = folderName;
    }

    @Override
    public boolean execute() {
        this.folder.deleteFolder(folderName);
        return true;
    }
    
}
