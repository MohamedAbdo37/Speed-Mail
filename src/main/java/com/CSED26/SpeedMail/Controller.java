package com.CSED26.SpeedMail;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
public class Controller {

    Server server = null;

    @PostMapping("/logIn")
    public User logIN(@RequestBody String address, @RequestBody String password) throws IOException {
        while (server != null) {
            server = Server.acquire();
        }
        User user = server.logIn(address, password);
        server.release();
        return user;
    }

    @PostMapping("/register")
    public User register(@RequestBody String name, @RequestBody String address, @RequestBody String password)
            throws IOException {
        while (server != null) {
            server = Server.acquire();
        }
        User user = server.register(name, address, password);
        server.release();
        return user;
    }

}
