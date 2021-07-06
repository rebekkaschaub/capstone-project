package de.neuefische.backend.service;

import de.neuefische.backend.model.*;
import de.neuefische.backend.repos.CounselingCenterRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class BookmarkServiceTest {

    private final CounselingCenterRepo repo = mock(CounselingCenterRepo.class);
    private final BookmarkService service = new BookmarkService(repo);


    @Test
    @DisplayName("method updateBookmarkBy should add user to bookmarked by, when user is not yet in bookmarkedBy list")
    void updateBookMarkedByAdd() {
        //GIVEN
        when(repo.findById("42")).thenReturn(Optional.of( CounselingCenter.builder()
                .id("42")
                .name("Test Beratung ")
                .bookmarkedBy(List.of("Michael", "Janosch", "Carsten"))
                .build()));

        CounselingCenter expected = CounselingCenter.builder()
            .id("42")
            .name("Test Beratung ")
            .bookmarkedBy(List.of("Michael", "Janosch", "Carsten","Saskia"))
            .build();

        when(repo.save(expected)).thenReturn(expected);

        //WHEN
        CounselingCenter actual = service.updateBookMarkedBy("42", "Saskia");

        //THEN
        assertThat(actual, is(expected));
        verify(repo).save(expected);
    }

    @Test
    @DisplayName("method updateBookmarkBy should remove user to bookmarked by, when user is in bookmarkedBy list")
    void updateBookMarkedByRemove() {
        //GIVEN
        when(repo.findById("42")).thenReturn(Optional.of( CounselingCenter.builder()
                .id("42")
                .name("Test Beratung ")
                .bookmarkedBy(List.of("Michael", "Janosch", "Carsten", "Saskia"))
                .build()));

        CounselingCenter expected = CounselingCenter.builder()
                .id("42")
                .name("Test Beratung ")
                .bookmarkedBy(List.of("Michael", "Janosch", "Carsten"))
                .build();

        when(repo.save(expected)).thenReturn(expected);

        //WHEN
        CounselingCenter actual = service.updateBookMarkedBy("42", "Saskia");

        //THEN
        assertThat(actual, is(expected));
        verify(repo).save(expected);
    }

    @Test
    @DisplayName("method updateBookmarkBy should throw NOTFOUND, when id dies not exist")
    void updateBookMarkedByNotExistingID() {
        //GIVEN
        when(repo.findById("42")).thenReturn(Optional.empty());

        //WHEN/THEN
        assertThrows(ResponseStatusException.class, ()->service.updateBookMarkedBy("42", "John"));

    }
}