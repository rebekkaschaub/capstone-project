package de.neuefische.backend.service;

import de.neuefische.backend.dto.LoginDataDto;
import de.neuefische.backend.dto.UserDto;
import de.neuefische.backend.security.model.AppUser;
import de.neuefische.backend.security.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SignUpService {

    private final AppUserRepository repo;
    private final  PasswordEncoder encoder;

    @Autowired
    public SignUpService(AppUserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }


    public UserDto signUp(LoginDataDto data) {
        if(repo.existsById(data.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        if(data.getUsername().length()<3){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username not valid");
        }

        if(!validatePassword(data.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password not valid");
        }

        repo.save(AppUser.builder()
                .username(data.getUsername())
                .password(encoder.encode(data.getPassword()))
                .build());
        return new UserDto(data.getUsername());
    }

    public boolean validatePassword(String password) {
        return containsLowercase(password) && containsUppercase(password) && containsNumber(password) && hasMinLength(password);
    }

    private boolean hasMinLength(String password) {
        return password.length()>=8;
    }

    private boolean containsLowercase(String password) {
       return password.matches(".*[a-z].*");
    }

    private boolean containsUppercase(String password) {
        return password.matches(".*[A-Z].*");
    }

    private boolean containsNumber(String password) {
        return password.matches((".*[0-9].*"));
    }
}
