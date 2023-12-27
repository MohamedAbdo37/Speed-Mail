package com.csed26.speedmail;

import com.csed26.speedmail.mail.Mail;

public interface ServerIF {
    public Mail getMail(String id);
    
    public Mail[] getFolder(String folder, String address);

}
