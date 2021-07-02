package de.neuefische.backend.controller;

import de.neuefische.backend.dto.LoginData;
import de.neuefische.backend.security.model.AppUser;
import de.neuefische.backend.security.repository.AppUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "jwt.secret=topSecret")
class LoginControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void loginWithValidCredentialsShouldReturnValidJwtToken() {
        //GIVEN
        appUserRepository.save(AppUser.builder().username("Test User").password(passwordEncoder.encode("testPa$$word")).build());

        //WHEN
        LoginData loginData = LoginData.builder().username("Test User").password("testPa$$word").build();
        ResponseEntity<String> response = testRestTemplate.postForEntity("http://localhost:"+port+"/auth/login", loginData, String.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        Claims body = Jwts.parser().setSigningKey("topSecret").parseClaimsJws(response.getBody()).getBody();
        assertThat(body.getSubject(), is("Test User"));
    }

    @Test
    void loginWithInvalidCredentialsShouldReturnUnauthorized() {
        //GIVEN
        appUserRepository.save(AppUser.builder().username("Test User").password(passwordEncoder.encode("testPa$$word")).build());

        //WHEN
        LoginData loginData = LoginData.builder().username("Test User").password("wrongPa$$word").build();
        ResponseEntity<String> response = testRestTemplate.postForEntity("http://localhost:"+port+"/auth/login", loginData, String.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.FORBIDDEN));

    }
}