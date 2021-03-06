package de.neuefische.backend.controller;

import de.neuefische.backend.dto.LoginDataDto;
import de.neuefische.backend.dto.ReviewDto;
import de.neuefische.backend.model.Review;
import de.neuefische.backend.model.ReviewStats;
import de.neuefische.backend.repos.ReviewRepo;
import de.neuefische.backend.security.model.AppUser;
import de.neuefische.backend.security.repository.AppUserRepository;
import de.neuefische.backend.utils.IdUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "jwt.secret=testSecret")
class ReviewControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder encoder;

    @MockBean
    private IdUtils idUtils;

    private String getUrl() {
        return "http://localhost:" + port + "/api/reviews";
    }

    @BeforeEach
    public void clearDb() {
        reviewRepo.deleteAll();
    }

    @Test
    @DisplayName("method listAllReviewsOfUser should return a list of all reviews from the logged in user")
    void listAllReviewsOfUser() {
        //GIVEN
        reviewRepo.saveAll(List.of(
                Review.builder()
                        .reviewId("42")
                        .counselingCenterId("123")
                        .counselingCenterName("Phobieberatung")
                        .author("Franzi")
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
                        .build(),
                Review.builder()
                        .reviewId("95")
                        .counselingCenterId("445")
                        .counselingCenterName("Suchtberatung")
                        .author("Franzi")
                        .title("TestTitle")
                        .rating(2)
                        .comment("testComment")
                        .build()
        ));

        String url = getUrl();

        //WHEN
        HttpHeaders headers = getHttpHeaderWithAuthToken();
        ResponseEntity<Review[]> response = testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Review[].class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), arrayContainingInAnyOrder(Review.builder()
                        .reviewId("42")
                        .counselingCenterId("123")
                        .counselingCenterName("Phobieberatung")
                        .author("Franzi")
                        .title("War gut")
                        .rating(5)
                        .comment("Mega! 5 Sterne")
                        .build(),
                Review.builder()
                        .reviewId("95")
                        .counselingCenterId("445")
                        .counselingCenterName("Suchtberatung")
                        .author("Franzi")
                        .title("TestTitle")
                        .rating(2)
                        .comment("testComment")
                        .build()));
    }

    @Test
    @DisplayName("method listAllReviewsOfCounselingCenter should return a list of all reviews about the Counseling Center with id 445")
    void listAllReviewsOfCounselingCenter() {
        //GIVEN
        reviewRepo.saveAll(List.of(
                Review.builder()
                        .reviewId("42")
                        .counselingCenterId("123")
                        .counselingCenterName("Phobieberatung")
                        .author("Franzi")
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
                        .build(),
                Review.builder()
                        .reviewId("95")
                        .counselingCenterId("445")
                        .counselingCenterName("Suchtberatung")
                        .author("Franzi")
                        .title("TestTitle")
                        .rating(2)
                        .comment("testComment")
                        .build()
        ));

        String url = getUrl() + "/445";

        //WHEN
        HttpHeaders headers = getHttpHeaderWithAuthToken();
        ResponseEntity<Review[]> response = testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Review[].class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), arrayContainingInAnyOrder(Review.builder()
                        .reviewId("90")
                        .counselingCenterId("445")
                        .counselingCenterName("Suchtberatung")
                        .author("DerTrizeps")
                        .title("Passt")
                        .rating(3)
                        .comment("Geht klar!")
                        .build(),
                Review.builder()
                        .reviewId("95")
                        .counselingCenterId("445")
                        .counselingCenterName("Suchtberatung")
                        .author("Franzi")
                        .title("TestTitle")
                        .rating(2)
                        .comment("testComment")
                        .build()));
    }

    @Test
    @DisplayName("method addReview should add a new review to db")
    void addReview() {
        //GIVEN
        ReviewDto reviewDto = ReviewDto.builder()
                .counselingCenterId("123")
                .counselingCenterName("Phobieberatung")
                .author("Franzi")
                .title("War gut")
                .rating(5)
                .comment("Mega! 5 Sterne").build();

        when(idUtils.generateUuid()).thenReturn("42");
        String url = getUrl();

        //WHEN
        HttpHeaders headers = getHttpHeaderWithAuthToken();
        ResponseEntity<Review> response = testRestTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(reviewDto, headers), Review.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(Review.builder()
                .reviewId("42")
                .counselingCenterId("123")
                .counselingCenterName("Phobieberatung")
                .author("Franzi")
                .title("War gut")
                .rating(5)
                .comment("Mega! 5 Sterne")
                .build()));

        List<Review> reviews = (List<Review>) reviewRepo.findAll();
        assertThat(reviews, hasItem(Review.builder()
                .reviewId("42")
                .counselingCenterId("123")
                .counselingCenterName("Phobieberatung")
                .author("Franzi")
                .title("War gut")
                .rating(5)
                .comment("Mega! 5 Sterne")
                .build()));
    }

    @Test
    @DisplayName("method addReview should throw HttpStatus FORBIDDEN, when author does not match the logged in user")
    void addReviewShouldThrowHttpStatusForbidden() {
        //GIVEN
        ReviewDto reviewDto = ReviewDto.builder()
                .counselingCenterId("123")
                .counselingCenterName("Phobieberatung")
                .author("Manuela")
                .title("War gut")
                .rating(5)
                .comment("Mega! 5 Sterne").build();

        String url = getUrl();

        //WHEN
        HttpHeaders headers = getHttpHeaderWithAuthToken();
        ResponseEntity<Review> response = testRestTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(reviewDto, headers), Review.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.FORBIDDEN));
    }

    @Test
    @DisplayName("method updateReview should update review with id 42")
    void updateReview() {
        //GIVEN
        reviewRepo.saveAll(List.of(
                Review.builder()
                        .reviewId("42")
                        .counselingCenterId("123")
                        .counselingCenterName("Phobieberatung")
                        .author("Franzi")
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
                        .build()));

        ReviewDto reviewDto = ReviewDto.builder()
                .counselingCenterId("123")
                .counselingCenterName("Phobieberatung")
                .author("Franzi")
                .title("changed title")
                .rating(3)
                .comment("changed comment").build();

        String url = getUrl() + "/42";

        //WHEN
        HttpHeaders headers = getHttpHeaderWithAuthToken();
        ResponseEntity<Review> response = testRestTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(reviewDto, headers), Review.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(Review.builder()
                .reviewId("42")
                .counselingCenterId("123")
                .counselingCenterName("Phobieberatung")
                .author("Franzi")
                .title("changed title")
                .rating(3)
                .comment("changed comment").build()));

        List<Review> reviews = (List<Review>) reviewRepo.findAll();
        assertThat(reviews, hasItem(Review.builder()
                .reviewId("42")
                .counselingCenterId("123")
                .counselingCenterName("Phobieberatung")
                .author("Franzi")
                .title("changed title")
                .rating(3)
                .comment("changed comment").build()));
    }


    @Test
    @DisplayName("method updateReview should throw HttpStatus FORBIDDEN, when author does not match the logged in user")
    void updateReviewShouldThrowHttpStatusForbidden() {
        //GIVEN
        ReviewDto reviewDto = ReviewDto.builder()
                .counselingCenterId("123")
                .counselingCenterName("Phobieberatung")
                .author("Michael")
                .title("changed title")
                .rating(3)
                .comment("changed comment").build();

        String url = getUrl() + "/42";

        //WHEN
        HttpHeaders headers = getHttpHeaderWithAuthToken();
        ResponseEntity<Review> response = testRestTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(reviewDto, headers), Review.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.FORBIDDEN));
    }

    @Test
    @DisplayName("method updateReview should throw HttpStatus NOT_FOUND, when review does not exist")
    void updateReviewShouldThrowHttpStatusNotFound() {
        //GIVEN
        reviewRepo.saveAll(List.of(
                Review.builder()
                        .reviewId("42")
                        .counselingCenterId("123")
                        .counselingCenterName("Phobieberatung")
                        .author("Franzi")
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
                        .build()));

        ReviewDto reviewDto = ReviewDto.builder()
                .counselingCenterId("123")
                .counselingCenterName("Phobieberatung")
                .author("Franzi")
                .title("changed title")
                .rating(3)
                .comment("changed comment").build();

        String url = getUrl() + "/43";

        //WHEN
        HttpHeaders headers = getHttpHeaderWithAuthToken();
        ResponseEntity<Review> response = testRestTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(reviewDto, headers), Review.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    @DisplayName("method deleteReview should delete Review by id")
    void deleteReview() {
        //GIVEN
        reviewRepo.saveAll(List.of(
                Review.builder()
                        .reviewId("42")
                        .counselingCenterId("123")
                        .counselingCenterName("Phobieberatung")
                        .author("Franzi")
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
                        .build(),
                Review.builder()
                        .reviewId("95")
                        .counselingCenterId("445")
                        .counselingCenterName("Suchtberatung")
                        .author("Franzi")
                        .title("TestTitle")
                        .rating(2)
                        .comment("testComment")
                        .build()));

        String url = getUrl() + "/42";

        //WHEN
        HttpHeaders headers = getHttpHeaderWithAuthToken();
        testRestTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(headers), void.class);


        //THEN
        List<Review> reviews = (List<Review>) reviewRepo.findAll();
        assertThat(reviews, containsInAnyOrder(Review.builder()
                .reviewId("90")
                .counselingCenterId("445")
                .counselingCenterName("Suchtberatung")
                .author("DerTrizeps")
                .title("Passt")
                .rating(3)
                .comment("Geht klar!")
                .build(), Review.builder()
                .reviewId("95")
                .counselingCenterId("445")
                .counselingCenterName("Suchtberatung")
                .author("Franzi")
                .title("TestTitle")
                .rating(2)
                .comment("testComment")
                .build()));
    }

    @Test
    @DisplayName("method getReviewStats should return ReviewStats for a CounselingCenter with id 42")
    void getReviewStats() {
        //GIVEN
        reviewRepo.saveAll(List.of(
                Review.builder()
                        .reviewId("42")
                        .counselingCenterId("123")
                        .counselingCenterName("Phobieberatung")
                        .author("Franzi")
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
                        .build(),
                Review.builder()
                        .reviewId("91")
                        .counselingCenterId("123")
                        .counselingCenterName("Phobieberatung")
                        .author("Franzi")
                        .title("Test Title")
                        .rating(3)
                        .build(),
                Review.builder()
                        .reviewId("92")
                        .counselingCenterId("123")
                        .counselingCenterName("Phobieberatung")
                        .author("Franzi")
                        .title("testTitle")
                        .rating(2)
                        .build()));

        String url = getUrl() + "/stats/123";

        //WHEN
        HttpHeaders headers = getHttpHeaderWithAuthToken();
        ResponseEntity<ReviewStats> response = testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), ReviewStats.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(ReviewStats.builder().average(3).count(3).build()));


    }

    private HttpHeaders getHttpHeaderWithAuthToken() {
        appUserRepository.save(AppUser.builder().username("Franzi").password(encoder.encode("test_password")).build());
        LoginDataDto loginData = new LoginDataDto("Franzi", "test_password");
        ResponseEntity<String> tokenResponse = testRestTemplate.postForEntity("http://localhost:" + port + "/auth/login", loginData, String.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenResponse.getBody());
        return headers;
    }
}