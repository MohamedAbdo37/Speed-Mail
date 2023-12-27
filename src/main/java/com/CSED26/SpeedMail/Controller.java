package com.csed26.speedmail;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
public class Controller {

    @PostMapping("/login")
    public User logIN(@RequestParam String address, @RequestParam String password) {
        User user = null;
        try {
            user = Server.getServer().logIn(address, password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @PostMapping("/register")
    public User register(@RequestParam String name, @RequestParam String address, @RequestParam String password) {
        User user;
        try {
            Server server = Server.getServer();
            user = server.register(name, address, password);
        } catch (IOException e) {
            e.getStackTrace();
            System.out.println("email already exist");
            return null;
        }
        return user;
    }

}
