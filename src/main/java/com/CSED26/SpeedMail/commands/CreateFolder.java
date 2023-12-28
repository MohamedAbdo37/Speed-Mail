package com.csed26.speedmail.commands;

import java.io.IOException;

import com.csed26.speedmail.Folder;
import com.csed26.speedmail.User;

public class CreateFolder implements Command{

    private User user;
    private String folderName;
    
    public CreateFolder(User user, String folderName ){
        this.user = user;
        this.folderName = folderName;
    }

    @Override
    public boolean execute() {
        try {
            this.user.mainFolder().folder(Folder.inBox).createFolder(this.folderName);
            this.user.addFolder(folderName);
        } catch (IOException e) {
            System.out.println("faild to create folder");
            return false;
        }
        return true;
    }
    
}
