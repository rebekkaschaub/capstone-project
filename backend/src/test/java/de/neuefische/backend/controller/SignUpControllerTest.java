package de.neuefische.backend.controller;

import de.neuefische.backend.dto.LoginDataDto;
import de.neuefische.backend.dto.UserDto;
import de.neuefische.backend.security.model.AppUser;
import de.neuefische.backend.security.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SignUpControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    private AppUserRepository repo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TestRestTemplate testRestTemplate;

    private String getUrl(){
        return "http://localhost:"+ port +"/auth/signup";
    }

    @BeforeEach
    public void setupDb() {
        repo.deleteAll();
        repo.save(AppUser.builder()
                .username("Lola123")
                .password(encoder.encode("TestPasswort"))
                .build());
    }


    @Test
    public void signUpWithValidCredentials(){
        //GIVEN
        String url = getUrl();
        LoginDataDto newUser = LoginDataDto.builder().username("crazy_cat_lady").password("IloveCats12").build();

        //WHEN
        ResponseEntity<UserDto> response = testRestTemplate.postForEntity(url, newUser, UserDto.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(new UserDto("crazy_cat_lady")));
    }

    @Test
    public void signUpWithInValidPassword(){
        //GIVEN
        String url = getUrl();
        LoginDataDto newUser = LoginDataDto.builder().username("crazy_cat_lady").password("IloveCats").build();

        //WHEN
        ResponseEntity<UserDto> response = testRestTemplate.postForEntity(url, newUser, UserDto.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void signUpWithInValidUsername(){
        //GIVEN
        String url = getUrl();
        LoginDataDto newUser = LoginDataDto.builder().username("LO").password("IloveCats").build();

        //WHEN
        ResponseEntity<UserDto> response = testRestTemplate.postForEntity(url, newUser, UserDto.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void signUpWithUsernameThatAlreadyExists(){
        //GIVEN
        String url = getUrl();
        LoginDataDto newUser = LoginDataDto.builder().username("Lola123").password("IloveCats1").build();

        //WHEN
        ResponseEntity<UserDto> response = testRestTemplate.postForEntity(url, newUser, UserDto.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.CONFLICT));
    }
}