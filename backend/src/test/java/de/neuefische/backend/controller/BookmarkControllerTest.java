package de.neuefische.backend.controller;

import de.neuefische.backend.dto.CounselingCenterDto;
import de.neuefische.backend.dto.LoginDataDto;
import de.neuefische.backend.model.*;
import de.neuefische.backend.repos.CounselingCenterRepo;
import de.neuefische.backend.security.model.AppUser;
import de.neuefische.backend.security.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "jwt.secret=testSecret")
class BookmarkControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private CounselingCenterRepo counselingCenterRepo;

    @Autowired
    private AppUserRepository appUserRepository;

    @BeforeEach
    public void clearDb() {
        counselingCenterRepo.deleteAll();
    }

    @Autowired
    private PasswordEncoder encoder;

    private String getUrl(){
        return "http://localhost:"+ port +"/api/bookmark";
    }
    @Test
    @DisplayName("method listBookmarkedCounselingCenters should list all bookmarked Counseling Centers of logged-in User")
    void listBookmarkedCounselingCenters() {
        //GIVEN
        counselingCenterRepo.saveAll(List.of(
                CounselingCenter.builder()
                        .id("777")
                        .name("Beste Beratung")
                        .address(Address.builder().street("Schokoladenstraße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("000")
                        .email("IloveChocolate@gmx.de")
                        .url("beste url")
                        .specializations(List.of(Specialization.ERZIEHUNGSBERATUNG,Specialization.PSYCHISCH, Specialization.ALLEINERZIEHENDE))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                        .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                        .bookmarkedBy(List.of("Franzi", "Janosch", "Saskia")).build(),
                CounselingCenter.builder()
                        .id("333")
                        .name("Test Beratung ")
                        .address(Address.builder().street("TestStraße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("040 280140-620")
                        .email("testmail")
                        .url("test url ")
                        .specializations(List.of(Specialization.PSYCHISCH, Specialization.SEXUALBERATUNG, Specialization.GEWALTTAETER))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.GROUP, CounselingSetting.PHONE))
                        .bookmarkedBy(List.of("Franzi", "Kim", "Leonie")).build(),
                CounselingCenter.builder()
                        .id("444")
                        .name("Super Beratung ")
                        .address(Address.builder().street("Super Straße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("11111")
                        .url("super url")
                        .specializations(List.of(Specialization.PSYCHISCH, Specialization.LEBENSBERATUNG, Specialization.LSBTIQ))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.GROUP, CounselingSetting.INPERSON))
                        .bookmarkedBy(List.of("Eva", "Merlin", "Natalia")).build()));

        String url = getUrl();

        //WHEN
        HttpHeaders headers = getHttpHeaderWithAuthToken();
        ResponseEntity<CounselingCenter[]> response = testRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>( headers), CounselingCenter[].class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), arrayContainingInAnyOrder(
                CounselingCenter.builder()
                        .id("777")
                        .name("Beste Beratung")
                        .address(Address.builder().street("Schokoladenstraße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("000")
                        .email("IloveChocolate@gmx.de")
                        .url("beste url")
                        .specializations(List.of(Specialization.ERZIEHUNGSBERATUNG,Specialization.PSYCHISCH, Specialization.ALLEINERZIEHENDE))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                        .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                        .bookmarkedBy(List.of("Franzi", "Janosch", "Saskia")).build(),
                CounselingCenter.builder()
                        .id("333")
                        .name("Test Beratung ")
                        .address(Address.builder().street("TestStraße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("040 280140-620")
                        .email("testmail")
                        .url("test url ")
                        .specializations(List.of(Specialization.PSYCHISCH, Specialization.SEXUALBERATUNG, Specialization.GEWALTTAETER))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.GROUP, CounselingSetting.PHONE))
                        .bookmarkedBy(List.of("Franzi", "Kim", "Leonie")).build()));
    }



    @Test
    @DisplayName("method updateBookmark should remove User from BookmarkedBy, when User is already on the bookmarkedBy-List")
    void updateBookmarkedByRemove() {
        //GIVEN
        counselingCenterRepo.saveAll(List.of(
                CounselingCenter.builder()
                        .id("777")
                        .name("Beste Beratung")
                        .address(Address.builder().street("Schokoladenstraße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("000")
                        .email("IloveChocolate@gmx.de")
                        .url("beste url")
                        .specializations(List.of(Specialization.ERZIEHUNGSBERATUNG,Specialization.PSYCHISCH, Specialization.ALLEINERZIEHENDE))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                        .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                        .bookmarkedBy(List.of("Franzi", "Janosch", "Saskia")).build(),
                CounselingCenter.builder()
                        .id("333")
                        .name("Test Beratung ")
                        .address(Address.builder().street("TestStraße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("040 280140-620")
                        .email("testmail")
                        .url("test url ")
                        .specializations(List.of(Specialization.PSYCHISCH, Specialization.SEXUALBERATUNG, Specialization.GEWALTTAETER))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.GROUP, CounselingSetting.PHONE))
                        .bookmarkedBy(List.of("Franzi", "Kim", "Leonie")).build(),
                CounselingCenter.builder()
                        .id("444")
                        .name("Super Beratung ")
                        .address(Address.builder().street("Super Straße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("11111")
                        .url("super url")
                        .specializations(List.of(Specialization.PSYCHISCH, Specialization.LEBENSBERATUNG, Specialization.LSBTIQ))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.GROUP, CounselingSetting.INPERSON))
                        .bookmarkedBy(List.of("Eva", "Merlin", "Natalia")).build()));

        CounselingCenterDto counselingCenterDto = new CounselingCenterDto("777");
        String url = getUrl();

        //WHEN
        HttpHeaders headers = getHttpHeaderWithAuthToken();
        ResponseEntity<CounselingCenter> response = testRestTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(counselingCenterDto, headers), CounselingCenter.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(CounselingCenter.builder()
                .id("777")
                .name("Beste Beratung")
                .address(Address.builder().street("Schokoladenstraße").postalCode("21109").city("Hamburg").build())
                .phoneNo("000")
                .email("IloveChocolate@gmx.de")
                .url("beste url")
                .specializations(List.of(Specialization.ERZIEHUNGSBERATUNG,Specialization.PSYCHISCH, Specialization.ALLEINERZIEHENDE))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                .bookmarkedBy(List.of("Janosch", "Saskia")).build()));
    }

    @Test
    @DisplayName("method updateBookmark should add User to BookmarkedBy, when User is not on the bookmarkedBy-List")
    void updateBookmarkedByAdd() {
        //GIVEN
        counselingCenterRepo.saveAll(List.of(
                CounselingCenter.builder()
                        .id("777")
                        .name("Beste Beratung")
                        .address(Address.builder().street("Schokoladenstraße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("000")
                        .email("IloveChocolate@gmx.de")
                        .url("beste url")
                        .specializations(List.of(Specialization.ERZIEHUNGSBERATUNG,Specialization.PSYCHISCH, Specialization.ALLEINERZIEHENDE))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                        .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                        .bookmarkedBy(List.of("Franzi", "Janosch", "Saskia")).build(),
                CounselingCenter.builder()
                        .id("333")
                        .name("Test Beratung ")
                        .address(Address.builder().street("TestStraße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("040 280140-620")
                        .email("testmail")
                        .url("test url ")
                        .specializations(List.of(Specialization.PSYCHISCH, Specialization.SEXUALBERATUNG, Specialization.GEWALTTAETER))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.GROUP, CounselingSetting.PHONE))
                        .bookmarkedBy(List.of("Franzi", "Kim", "Leonie")).build(),
                CounselingCenter.builder()
                        .id("444")
                        .name("Super Beratung ")
                        .address(Address.builder().street("Super Straße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("11111")
                        .url("super url")
                        .specializations(List.of(Specialization.PSYCHISCH, Specialization.LEBENSBERATUNG, Specialization.LSBTIQ))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.GROUP, CounselingSetting.INPERSON))
                        .bookmarkedBy(List.of("Eva", "Merlin", "Natalia")).build()));

        CounselingCenterDto counselingCenterDto = new CounselingCenterDto("444");
        String url = getUrl();

        //WHEN
        HttpHeaders headers = getHttpHeaderWithAuthToken();
        ResponseEntity<CounselingCenter> response = testRestTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(counselingCenterDto, headers), CounselingCenter.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(CounselingCenter.builder()
                .id("444")
                .name("Super Beratung ")
                .address(Address.builder().street("Super Straße").postalCode("21109").city("Hamburg").build())
                .phoneNo("11111")
                .url("super url")
                .specializations(List.of(Specialization.PSYCHISCH, Specialization.LEBENSBERATUNG, Specialization.LSBTIQ))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                .counselingSetting(List.of(CounselingSetting.GROUP, CounselingSetting.INPERSON))
                .bookmarkedBy(List.of("Eva", "Merlin", "Natalia", "Franzi")).build()));

    }


    @Test
    @DisplayName("method updateBookmark should throw NOTFOUND, CounselingCenter does not exist")
    void updateBookmarkedByShouldThrowException() {
        //GIVEN
        counselingCenterRepo.saveAll(List.of(
                CounselingCenter.builder()
                        .id("777")
                        .name("Beste Beratung")
                        .address(Address.builder().street("Schokoladenstraße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("000")
                        .email("IloveChocolate@gmx.de")
                        .url("beste url")
                        .specializations(List.of(Specialization.ERZIEHUNGSBERATUNG,Specialization.PSYCHISCH, Specialization.ALLEINERZIEHENDE))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                        .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                        .bookmarkedBy(List.of("Franzi", "Janosch", "Saskia")).build(),
                CounselingCenter.builder()
                        .id("333")
                        .name("Test Beratung ")
                        .address(Address.builder().street("TestStraße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("040 280140-620")
                        .email("testmail")
                        .url("test url ")
                        .specializations(List.of(Specialization.PSYCHISCH, Specialization.SEXUALBERATUNG, Specialization.GEWALTTAETER))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.GROUP, CounselingSetting.PHONE))
                        .bookmarkedBy(List.of("Franzi", "Kim", "Leonie")).build(),
                CounselingCenter.builder()
                        .id("444")
                        .name("Super Beratung ")
                        .address(Address.builder().street("Super Straße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("11111")
                        .url("super url")
                        .specializations(List.of(Specialization.PSYCHISCH, Specialization.LEBENSBERATUNG, Specialization.LSBTIQ))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.GROUP, CounselingSetting.INPERSON))
                        .bookmarkedBy(List.of("Eva", "Merlin", "Natalia")).build()));

        CounselingCenterDto counselingCenterDto = new CounselingCenterDto("1");
        String url = getUrl();

        //WHEN
        HttpHeaders headers = getHttpHeaderWithAuthToken();
        ResponseEntity<CounselingCenter> response = testRestTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(counselingCenterDto, headers), CounselingCenter.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));

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