package com.CSED26.SpeedMail;

import java.io.File;
import java.io.IOException;

import com.CSED26.SpeedMail.Mail.Mail;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Data {
    

    public static void saveMail(Mail mail) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // File file = new File();
        // objectMapper.writeValue();
        System.out.println("Shapes successfully written to JSON file.");
    }

    public static Mail getMail(){
        return null;
    }
}
