package com.csed26.speedmail.commands;

import com.csed26.speedmail.Folder;

public class RenameFolder implements Command {

    private Folder folder;
    private String name;
    
    public RenameFolder(Folder folder, String name){
        this.folder = folder;
        this.name = name;
    }
    @Override
    public boolean execute() {
        this.folder.setFolderName(this.name);
        return true;
    }
    
}
