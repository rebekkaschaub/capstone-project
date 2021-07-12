package de.neuefische.backend.controller;

import de.neuefische.backend.dto.LoginDataDto;
import de.neuefische.backend.dto.UserDto;
import de.neuefische.backend.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService service;

    @PostMapping
    public UserDto signUp(@RequestBody LoginDataDto data) {
        return service.signUp(data);
    }

}
