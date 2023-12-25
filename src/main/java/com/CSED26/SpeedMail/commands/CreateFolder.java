package com.csed26.speedmail.commands;

import java.io.IOException;

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
            this.user.getMainFolder().createFolder(this.folderName);
        } catch (IOException e) {
            System.out.println("faild to create folder");
            return false;
        }
        return true;
    }
    
}
