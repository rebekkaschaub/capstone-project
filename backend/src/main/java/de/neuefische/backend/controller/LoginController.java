package de.neuefische.backend.controller;

import de.neuefische.backend.security.model.AppUser;
import de.neuefische.backend.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping
    public String login(@RequestBody AppUser data){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(data.getUsername(),data.getPassword());
        authenticationManager.authenticate(authentication);

        return jwtService.createToken(new HashMap<>(), data.getUsername());
    }


}
