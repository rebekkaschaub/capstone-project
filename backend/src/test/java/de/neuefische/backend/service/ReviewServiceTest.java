package de.neuefische.backend.service;

import de.neuefische.backend.dto.ReviewDto;
import de.neuefische.backend.model.Review;
import de.neuefische.backend.repos.ReviewRepo;
import de.neuefische.backend.utils.IdUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ReviewServiceTest {
    private final ReviewRepo repo = mock(ReviewRepo.class);
    private final IdUtils idUtils = mock(IdUtils.class);
    private final ReviewService service = new ReviewService(repo, idUtils);

    @BeforeEach
    public void clearDb() {
        repo.deleteAll();
    }

    @Test
    @DisplayName("method addReview should add a new Review to db")
    void addReviewThatDoesNotExistYet() {
        //GIVEN
        ReviewDto reviewDto = ReviewDto.builder()
                .counselingCenterId("123")
                .counselingCenterName("Phobieberatung")
                .author("DerBizeps")
                .title("War gut")
                .rating(5)
                .comment("Mega! 5 Sterne").build();

        Review expected = Review.builder()
                .reviewId("42")
                .counselingCenterId("123")
                .counselingCenterName("Phobieberatung")
                .author("DerBizeps")
                .title("War gut")
                .rating(5)
                .comment("Mega! 5 Sterne")
                .build();

        when(idUtils.generateUuid()).thenReturn("42");
        when(repo.save(expected)).thenReturn(expected);

        //WHEN
        Review actual = service.addReview(reviewDto);

        //THEN
        assertThat(actual, is(expected));
        verify(repo).save(expected);

    }

    @Test
    @DisplayName("method addReview should throw HttpStatus.Conflict, when a a review from the same user already exists for this Counseling Center")
    void addReviewThatDoesAlreadyExist() {
        //GIVEN
        ReviewDto reviewDto = ReviewDto.builder()
                .counselingCenterId("123")
                .counselingCenterName("Phobieberatung")
                .author("DerBizeps")
                .title("Ok")
                .rating(1)
                .comment("doch nur ganz ok").build();

        when(repo.existsByCounselingCenterIdAndAuthor(reviewDto.getCounselingCenterId(), reviewDto.getAuthor())).thenReturn(true);

        //WHEN/THEN
        assertThrows(ResponseStatusException.class, ()-> service.addReview(reviewDto));
    }

    @Test
    @DisplayName("method deleteReview should delete review by id")
    void deleteReviewWithMatchingUsername() {
        repo.saveAll(List.of(Review.builder()
                    .reviewId("42")
                    .counselingCenterId("123")
                    .counselingCenterName("Phobieberatung")
                    .author("DerBizeps")
                    .title("War gut")
                    .rating(5)
                    .comment("Mega! 5 Sterne")
                    .build(),
                Review.builder()
                    .reviewId("90")
                    .counselingCenterId("765")
                    .counselingCenterName("Suchtberatung")
                    .author("DerTrizeps")
                    .title("Supi")
                    .rating(4)
                    .comment("Alles ok")
                    .build()));

        when(repo.findById("42")).thenReturn(Optional.of(Review.builder()
                .reviewId("42")
                .counselingCenterId("123")
                .counselingCenterName("Phobieberatung")
                .author("DerBizeps")
                .title("War gut")
                .rating(5)
                .comment("Mega! 5 Sterne")
                .build()));

        //WHEN
        service.deleteReview("42", "DerBizeps");

        //THEN
        verify(repo).deleteById("42");
    }

    @Test
    @DisplayName("method deleteReview should throw HttpStatus Forbidden, when review with not matching username should be deleted")
    void deleteReviewWithNotMatchingUsername() {
        repo.saveAll(List.of(Review.builder()
                        .reviewId("42")
                        .counselingCenterId("123")
                        .counselingCenterName("Phobieberatung")
                        .author("DerBizeps")
                        .title("War gut")
                        .rating(5)
                        .comment("Mega! 5 Sterne")
                        .build(),
                Review.builder()
                        .reviewId("90")
                        .counselingCenterId("765")
                        .counselingCenterName("Suchtberatung")
                        .author("DerTrizeps")
                        .title("Supi")
                        .rating(4)
                        .comment("Alles ok")
                        .build()));

        when(repo.findById("42")).thenReturn(Optional.of(Review.builder()
                .reviewId("42")
                .counselingCenterId("123")
                .counselingCenterName("Phobieberatung")
                .author("DerBizeps")
                .title("War gut")
                .rating(5)
                .comment("Mega! 5 Sterne")
                .build()));

        //WHEN/THEN
        assertThrows(ResponseStatusException.class, ()->  service.deleteReview("42", "DerTrizeps"));
        verify(repo, never()).deleteById("42");
    }

    @Test
    void updateReview() {
    }
}