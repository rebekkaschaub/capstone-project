package de.neuefische.backend.service;

import de.neuefische.backend.dto.LoginDataDto;
import de.neuefische.backend.dto.UserDto;
import de.neuefische.backend.security.repository.AppUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SignUpServiceTest {

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();
    private final AppUserRepository repo = mock(AppUserRepository.class);
    private final SignUpService signUpService = new SignUpService(repo, encoder);

    @Test
    @DisplayName("method signUp should return new userDto, when username does not already exist")
    void signUpTest() {
        //Given
        when(repo.existsById("Lucy")).thenReturn(false);
        LoginDataDto newUser = LoginDataDto.builder().username("Lucy").password("1234abCD").build();

        // When
        UserDto actual = signUpService.signUp(newUser);

        // Then
        assertThat(actual, is(new UserDto("Lucy")));
    }

    @Test
    @DisplayName("method signUp should should throw HttpStatus.CONFLICT, when username already exists")
    void signUpWithExistingUserNameTest() {
        //Given
        when(repo.existsById("Lucy")).thenReturn(true);
        LoginDataDto newUser = LoginDataDto.builder().username("Lucy").password("1234abCD").build();

        // When/ Then
        assertThrows(ResponseStatusException.class, () -> signUpService.signUp(newUser));
    }

    @Test
    @DisplayName("method signUp should should throw HttpStatus.BAD_REQUEST, when password is not valid")
    void signUpWithInvalidPassword() {
        //Given
        when(repo.existsById("Lucy")).thenReturn(false);
        LoginDataDto newUser = LoginDataDto.builder().username("Lucy").password("123").build();

        // When/ Then
        assertThrows(ResponseStatusException.class, () -> signUpService.signUp(newUser));
    }

    @ParameterizedTest(name = "when password is {0}, then validatePassword should return: {1}")
    @CsvSource({
            "12345abCD, true",
            "123456789, false",
            "123abCD, false",
            "1234ABCD, false",
            "abcdEFGH, false",
            "abcdefgh, false",
            "ABCDEFGH, false"
    })
    public void validatePasswordTest(String password, boolean expected) {
        //WHEN
        boolean response = signUpService.validatePassword(password);

        // THEN
        assertEquals(expected, response);
    }
}