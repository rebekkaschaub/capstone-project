package de.neuefische.backend.security.service;

import de.neuefische.backend.security.model.AppUser;
import de.neuefische.backend.security.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppUserDetailsServiceTest {

    private final AppUserRepository appUserRepository = mock(AppUserRepository.class);
    private final AppUserDetailsService appUserDetailsService = new AppUserDetailsService(appUserRepository);

    @Test
    public void findUSerByName(){
        //GIVEN
        when(appUserRepository.findById("Jürni")).thenReturn(Optional.of(AppUser.builder().username("Jürni").password("SuperPasswort").build()));

        //WHEN
        UserDetails actual = appUserDetailsService.loadUserByUsername("Jürni");

        //THEN
        assertThat(actual.getUsername(), is("Jürni"));
        assertThat(actual.getPassword(), is("SuperPasswort"));
    }

    @Test
    public void throwsWhenUsernameIsNotFound(){
        //GIVEN
        when(appUserRepository.findById("Jürni")).thenReturn(Optional.empty());

        //WHEN/THEN
        assertThrows(UsernameNotFoundException.class, ()-> appUserDetailsService.loadUserByUsername("Jürni"));
    }


}