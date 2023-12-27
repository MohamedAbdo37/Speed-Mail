package com.csed26.speedmail.commands;


import com.csed26.speedmail.Folder;

public class DeleteFolder implements Command {

    private Folder folder;
    private String folderName;

    public DeleteFolder(Folder folder, String folderName){
        this.folder = folder;
        this.folderName = folderName;
    }

    @Override
    public boolean execute() {
        this.folder.deleteFolder(folderName);
        return true;
    }
    
}
