package de.neuefische.backend.controller;

import de.neuefische.backend.model.*;
import de.neuefische.backend.repos.CounselingCenterRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CounselingCenterControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private CounselingCenterRepo repo;

    @BeforeEach
    public void clearDb() {
        repo.deleteAll();
    }

    @Test
    @DisplayName("Method listAllCounselingCenters should return a list of all centers in repo")
    public void listAllCounselingCentersTest(){
        //GIVEN
        repo.save(CounselingCenter.builder()
                .id("1")
                .name("SEEHAUS Suchtberatungs- und Behandlungszentrum Wandsbek")
                .address(Address.builder().street("Hasselbrookstraße 94a").postalCode("22089").city("Hamburg").build())
                .phoneNo("040 2000102000")
                .email("info@seehaus-hh.de")
                .url("http://www.therapiehilfe.de")
                .specializations(List.of(Specialization.SUCHT, Specialization.PSYCHISCH))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE, CounselingSetting.GROUP))
                .supportGroups(false).build());
        repo.save(CounselingCenter.builder()
                .id("456")
                .name("Erziehungsberatungsstelle Billstedt")
                .address(Address.builder().street("Öjendorfer Weg 10a").postalCode("22111").city("Hamburg").build())
                .phoneNo("040 280140-620")
                .email("erziehungsberatung@caritas-hamburg.de")
                .url("http://www.caritas-hamburg.de ")
                .specializations(List.of(Specialization.ERZIEHUNGSBERATUNG,Specialization.KINDER, Specialization.ALLEINERZIEHENDE))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                .supportGroups(true).build());

        //WHEN
        ResponseEntity<CounselingCenter[]> response= testRestTemplate.getForEntity("http://localhost:"+ port +"/api/counseling", CounselingCenter[].class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), arrayContainingInAnyOrder(
                CounselingCenter.builder()
                        .id("1")
                        .name("SEEHAUS Suchtberatungs- und Behandlungszentrum Wandsbek")
                        .address(Address.builder().street("Hasselbrookstraße 94a").postalCode("22089").city("Hamburg").build())
                        .phoneNo("040 2000102000")
                        .email("info@seehaus-hh.de")
                        .url("http://www.therapiehilfe.de")
                        .specializations(List.of(Specialization.SUCHT, Specialization.PSYCHISCH))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE, CounselingSetting.GROUP))
                        .supportGroups(false).build(),
                CounselingCenter.builder()
                        .id("456")
                        .name("Erziehungsberatungsstelle Billstedt")
                        .address(Address.builder().street("Öjendorfer Weg 10a").postalCode("22111").city("Hamburg").build())
                        .phoneNo("040 280140-620")
                        .email("erziehungsberatung@caritas-hamburg.de")
                        .url("http://www.caritas-hamburg.de ")
                        .specializations(List.of(Specialization.ERZIEHUNGSBERATUNG,Specialization.KINDER, Specialization.ALLEINERZIEHENDE))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                        .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                        .supportGroups(true).build()));
    }

    @Test
    @DisplayName("method getCounselingCenterById should return center with id 1")
    void getCounselingCenterById() {
        //GIVEN
        repo.save(CounselingCenter.builder()
                .id("1")
                .name("SEEHAUS Suchtberatungs- und Behandlungszentrum Wandsbek")
                .address(Address.builder().street("Hasselbrookstraße 94a").postalCode("22089").city("Hamburg").build())
                .phoneNo("040 2000102000")
                .email("info@seehaus-hh.de")
                .url("http://www.therapiehilfe.de")
                .specializations(List.of(Specialization.SUCHT, Specialization.PSYCHISCH))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE, CounselingSetting.GROUP))
                .supportGroups(false).build());
        repo.save(CounselingCenter.builder()
                .id("456")
                .name("Erziehungsberatungsstelle Billstedt")
                .address(Address.builder().street("Öjendorfer Weg 10a").postalCode("22111").city("Hamburg").build())
                .phoneNo("040 280140-620")
                .email("erziehungsberatung@caritas-hamburg.de")
                .url("http://www.caritas-hamburg.de ")
                .specializations(List.of(Specialization.ERZIEHUNGSBERATUNG,Specialization.KINDER, Specialization.ALLEINERZIEHENDE))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                .supportGroups(true).build());

        //WHEN
        ResponseEntity<CounselingCenter> response= testRestTemplate.getForEntity("http://localhost:"+ port +"/api/counseling/1", CounselingCenter.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(
                CounselingCenter.builder()
                        .id("1")
                        .name("SEEHAUS Suchtberatungs- und Behandlungszentrum Wandsbek")
                        .address(Address.builder().street("Hasselbrookstraße 94a").postalCode("22089").city("Hamburg").build())
                        .phoneNo("040 2000102000")
                        .email("info@seehaus-hh.de")
                        .url("http://www.therapiehilfe.de")
                        .specializations(List.of(Specialization.SUCHT, Specialization.PSYCHISCH))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE, CounselingSetting.GROUP))
                        .supportGroups(false).build()));
    }


    @Test
    @DisplayName("method getCounselingCenterById should throw exception when id does not exist")
    void getCounselingCenterByNotExistingId() {
        //GIVEN
        repo.save(CounselingCenter.builder()
                .id("1")
                .name("SEEHAUS Suchtberatungs- und Behandlungszentrum Wandsbek")
                .address(Address.builder().street("Hasselbrookstraße 94a").postalCode("22089").city("Hamburg").build())
                .phoneNo("040 2000102000")
                .email("info@seehaus-hh.de")
                .url("http://www.therapiehilfe.de")
                .specializations(List.of(Specialization.SUCHT, Specialization.PSYCHISCH))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE, CounselingSetting.GROUP))
                .supportGroups(false).build());
        repo.save(CounselingCenter.builder()
                .id("456")
                .name("Erziehungsberatungsstelle Billstedt")
                .address(Address.builder().street("Öjendorfer Weg 10a").postalCode("22111").city("Hamburg").build())
                .phoneNo("040 280140-620")
                .email("erziehungsberatung@caritas-hamburg.de")
                .url("http://www.caritas-hamburg.de ")
                .specializations(List.of(Specialization.ERZIEHUNGSBERATUNG,Specialization.KINDER, Specialization.ALLEINERZIEHENDE))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                .supportGroups(true).build());

        //WHEN
        ResponseEntity<CounselingCenter> response= testRestTemplate.getForEntity("http://localhost:"+ port +"/api/counseling/5", CounselingCenter.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));

    }

    @Test
    @DisplayName("method searchCounselingCenter should return all counseling centers matching the search parameters")
    void searchCounselingCenterWithValidSearchParamsAndMatchingResults() {
        //GIVEN
        repo.save(CounselingCenter.builder()
                .id("1")
                .name("SEEHAUS Suchtberatungs- und Behandlungszentrum Wandsbek")
                .address(Address.builder().street("Hasselbrookstraße 94a").postalCode("22089").city("Hamburg").build())
                .phoneNo("040 2000102000")
                .email("info@seehaus-hh.de")
                .url("http://www.therapiehilfe.de")
                .specializations(List.of(Specialization.SUCHT, Specialization.PSYCHISCH))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE, CounselingSetting.GROUP))
                .supportGroups(false).build());
        repo.save(CounselingCenter.builder()
                .id("456")
                .name("Erziehungsberatungsstelle Billstedt")
                .address(Address.builder().street("Öjendorfer Weg 10a").postalCode("22111").city("Hamburg").build())
                .phoneNo("040 280140-620")
                .email("erziehungsberatung@caritas-hamburg.de")
                .url("http://www.caritas-hamburg.de ")
                .specializations(List.of(Specialization.ERZIEHUNGSBERATUNG,Specialization.KINDER, Specialization.ALLEINERZIEHENDE))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                .supportGroups(true).build());

        //WHEN

        String searchUrl = "?specialization=SUCHT&specialization=PSYCHISCH&city=Hamburg&targetGroup=INDIVIDUAL&counselingSetting=PHONE&counselingSetting=GROUP";
        ResponseEntity<CounselingCenter[]> response = testRestTemplate.getForEntity("http://localhost:"+ port +"/api/counseling/search"+searchUrl, CounselingCenter[].class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), arrayContainingInAnyOrder(
                CounselingCenter.builder()
                        .id("1")
                        .name("SEEHAUS Suchtberatungs- und Behandlungszentrum Wandsbek")
                        .address(Address.builder().street("Hasselbrookstraße 94a").postalCode("22089").city("Hamburg").build())
                        .phoneNo("040 2000102000")
                        .email("info@seehaus-hh.de")
                        .url("http://www.therapiehilfe.de")
                        .specializations(List.of(Specialization.SUCHT, Specialization.PSYCHISCH))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE, CounselingSetting.GROUP))
                        .supportGroups(false).build()));
    }

    @Test
    @DisplayName("method searchCounselingCenter should return empty list")
    void searchCounselingCenterWithValidSearchParamsAndNoResults() {
        //GIVEN
        repo.save(CounselingCenter.builder()
                .id("1")
                .name("SEEHAUS Suchtberatungs- und Behandlungszentrum Wandsbek")
                .address(Address.builder().street("Hasselbrookstraße 94a").postalCode("22089").city("Hamburg").build())
                .phoneNo("040 2000102000")
                .email("info@seehaus-hh.de")
                .url("http://www.therapiehilfe.de")
                .specializations(List.of(Specialization.SUCHT, Specialization.PSYCHISCH))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE, CounselingSetting.GROUP))
                .supportGroups(false).build());
        repo.save(CounselingCenter.builder()
                .id("456")
                .name("Erziehungsberatungsstelle Billstedt")
                .address(Address.builder().street("Öjendorfer Weg 10a").postalCode("22111").city("Hamburg").build())
                .phoneNo("040 280140-620")
                .email("erziehungsberatung@caritas-hamburg.de")
                .url("http://www.caritas-hamburg.de ")
                .specializations(List.of(Specialization.ERZIEHUNGSBERATUNG,Specialization.KINDER, Specialization.ALLEINERZIEHENDE))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                .supportGroups(true).build());

        //WHEN

        String searchUrl = "?specialization=SUCHT&specialization=PSYCHISCH&city=Hamburg&targetGroup=INDIVIDUAL&counselingSetting=CHAT";
        ResponseEntity<CounselingCenter[]> response = testRestTemplate.getForEntity("http://localhost:"+ port +"/api/counseling/search"+searchUrl, CounselingCenter[].class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), emptyArray());
    }

    @Test
    @DisplayName("method searchCounselingCenter should throw BADREQUEST")
    void searchCounselingCenterWithInvalidSearchParams() {
        //GIVEN
        repo.save(CounselingCenter.builder()
                .id("1")
                .name("SEEHAUS Suchtberatungs- und Behandlungszentrum Wandsbek")
                .address(Address.builder().street("Hasselbrookstraße 94a").postalCode("22089").city("Hamburg").build())
                .phoneNo("040 2000102000")
                .email("info@seehaus-hh.de")
                .url("http://www.therapiehilfe.de")
                .specializations(List.of(Specialization.SUCHT, Specialization.PSYCHISCH))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE, CounselingSetting.GROUP))
                .supportGroups(false).build());
        repo.save(CounselingCenter.builder()
                .id("456")
                .name("Erziehungsberatungsstelle Billstedt")
                .address(Address.builder().street("Öjendorfer Weg 10a").postalCode("22111").city("Hamburg").build())
                .phoneNo("040 280140-620")
                .email("erziehungsberatung@caritas-hamburg.de")
                .url("http://www.caritas-hamburg.de ")
                .specializations(List.of(Specialization.ERZIEHUNGSBERATUNG,Specialization.KINDER, Specialization.ALLEINERZIEHENDE))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                .supportGroups(true).build());

        //WHEN

        String searchUrl = "?counselingSetting=NOTVALID";
        ResponseEntity<Void> response = testRestTemplate.getForEntity("http://localhost:"+ port +"/api/counseling/search"+searchUrl, Void.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }
}