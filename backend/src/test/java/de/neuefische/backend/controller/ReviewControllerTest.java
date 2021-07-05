package de.neuefische.backend.controller;

import de.neuefische.backend.model.Review;
import de.neuefische.backend.repos.CounselingCenterRepo;
import de.neuefische.backend.repos.ReviewRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private ReviewRepo repo;

    @BeforeEach
    public void clearDb() {
        repo.deleteAll();
    }

    @Test
    void listAllReviewsOfUser() {
        //GIVEN
        repo.saveAll(List.of(
                Review.builder()
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
                        .counselingCenterId("445")
                        .counselingCenterName("Suchtberatung")
                        .author("DerTrizeps")
                        .title("Passt")
                        .rating(3)
                        .comment("Geht klar!")
                        .build()
        ));

        //WHEN
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Review[]> response = testRestTemplate.exchange("http://localhost:" + port + "/api/reviews", HttpMethod.GET, new HttpEntity<>(headers),Review[].class);
    }

    @Test
    void listAllReviewsOfCounselingCenter() {
    }

    @Test
    void addReview() {
    }

    @Test
    void updateReview() {
    }

    @Test
    void deleteReview() {
    }
}