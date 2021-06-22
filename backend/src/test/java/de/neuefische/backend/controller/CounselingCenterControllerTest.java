package de.neuefische.backend.controller;

import de.neuefische.backend.dto.CounselingCenterQueryDto;
import de.neuefische.backend.model.Address;
import de.neuefische.backend.model.CounselingCenter;
import de.neuefische.backend.model.CounselingSetting;
import de.neuefische.backend.model.TargetGroup;
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
                .id("123")
                .name("SEEHAUS Suchtberatungs- und Behandlungszentrum Wandsbek")
                .address(Address.builder().street("Hasselbrookstraße 94a").postalCode("22089").city("Hamburg").build())
                .phoneNo("040 2000102000")
                .email("info@seehaus-hh.de")
                .url("http://www.therapiehilfe.de")
                .specializations(List.of("Suchtberatung", "Vermittlung von Selbsthilfegruppen", "Gruppenarbeit", "Krisenintervention"))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE)).build());
        repo.save(CounselingCenter.builder()
                .id("456")
                .name("Erziehungsberatungsstelle Billstedt")
                .address(Address.builder().street("Öjendorfer Weg 10a").postalCode("22111").city("Hamburg").build())
                .phoneNo("040 280140-620")
                .email("erziehungsberatung@caritas-hamburg.de")
                .url("http://www.caritas-hamburg.de ")
                .specializations(List.of("Erziehungsberatung", "Beratung für Kinder", "Jugendliche und Eltern (einschl. Beratung bei Trennung und Scheidung)" ))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE)).build());

        //WHEN
        ResponseEntity<CounselingCenter[]> response= testRestTemplate.getForEntity("http://localhost:"+ port +"/api/counseling", CounselingCenter[].class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), arrayContainingInAnyOrder(
                CounselingCenter.builder()
                        .id("123")
                        .name("SEEHAUS Suchtberatungs- und Behandlungszentrum Wandsbek")
                        .address(Address.builder().street("Hasselbrookstraße 94a").postalCode("22089").city("Hamburg").build())
                        .phoneNo("040 2000102000")
                        .email("info@seehaus-hh.de")
                        .url("http://www.therapiehilfe.de")
                        .specializations(List.of("Suchtberatung", "Vermittlung von Selbsthilfegruppen", "Gruppenarbeit", "Krisenintervention"))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                        .build(),
                CounselingCenter.builder()
                        .id("456")
                        .name("Erziehungsberatungsstelle Billstedt")
                        .address(Address.builder().street("Öjendorfer Weg 10a").postalCode("22111").city("Hamburg").build())
                        .phoneNo("040 280140-620")
                        .email("erziehungsberatung@caritas-hamburg.de")
                        .url("http://www.caritas-hamburg.de ")
                        .specializations(List.of("Erziehungsberatung", "Beratung für Kinder", "Jugendliche und Eltern (einschl. Beratung bei Trennung und Scheidung)" ))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                        .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                        .build()));
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
                .specializations(List.of("Suchtberatung", "Vermittlung von Selbsthilfegruppen", "Gruppenarbeit", "Krisenintervention"))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE)).build());
        repo.save(CounselingCenter.builder()
                .id("456")
                .name("Erziehungsberatungsstelle Billstedt")
                .address(Address.builder().street("Öjendorfer Weg 10a").postalCode("22111").city("Hamburg").build())
                .phoneNo("040 280140-620")
                .email("erziehungsberatung@caritas-hamburg.de")
                .url("http://www.caritas-hamburg.de ")
                .specializations(List.of("Erziehungsberatung", "Beratung für Kinder", "Jugendliche und Eltern (einschl. Beratung bei Trennung und Scheidung)" ))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE)).build());

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
                        .specializations(List.of("Suchtberatung", "Vermittlung von Selbsthilfegruppen", "Gruppenarbeit", "Krisenintervention"))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                        .build()));
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
                .specializations(List.of("Suchtberatung", "Vermittlung von Selbsthilfegruppen", "Gruppenarbeit", "Krisenintervention"))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE)).build());
        repo.save(CounselingCenter.builder()
                .id("456")
                .name("Erziehungsberatungsstelle Billstedt")
                .address(Address.builder().street("Öjendorfer Weg 10a").postalCode("22111").city("Hamburg").build())
                .phoneNo("040 280140-620")
                .email("erziehungsberatung@caritas-hamburg.de")
                .url("http://www.caritas-hamburg.de ")
                .specializations(List.of("Erziehungsberatung", "Beratung für Kinder", "Jugendliche und Eltern (einschl. Beratung bei Trennung und Scheidung)" ))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE)).build());

        //WHEN
        ResponseEntity<CounselingCenter> response= testRestTemplate.getForEntity("http://localhost:"+ port +"/api/counseling/5", CounselingCenter.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));

    }

    @Test
    void filterCounselingCenter() {
        //GIVEN
        repo.save(CounselingCenter.builder()
                .id("123")
                .name("SEEHAUS Suchtberatungs- und Behandlungszentrum Wandsbek")
                .address(Address.builder().street("Hasselbrookstraße 94a").postalCode("22089").city("Hamburg").build())
                .phoneNo("040 2000102000")
                .email("info@seehaus-hh.de")
                .url("http://www.therapiehilfe.de")
                .specializations(List.of("Suchtberatung", "Vermittlung von Selbsthilfegruppen", "Gruppenarbeit", "Krisenintervention"))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE)).build());
        repo.save(CounselingCenter.builder()
                .id("456")
                .name("Erziehungsberatungsstelle Billstedt")
                .address(Address.builder().street("Öjendorfer Weg 10a").postalCode("22111").city("Hamburg").build())
                .phoneNo("040 280140-620")
                .email("erziehungsberatung@caritas-hamburg.de")
                .url("http://www.caritas-hamburg.de ")
                .specializations(List.of("Erziehungsberatung", "Beratung für Kinder", "Jugendliche und Eltern (einschl. Beratung bei Trennung und Scheidung)" ))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE)).build());

        //WHEN

        CounselingCenterQueryDto filter = CounselingCenterQueryDto.builder().city("Hamburg").postalCode("22089").specialization("Suchtberatung").targetGroup(List.of(TargetGroup.INDIVIDUAL)).counselingSetting(List.of(CounselingSetting.INPERSON)).build();
        ResponseEntity<CounselingCenter[]> response = testRestTemplate.postForEntity("http://localhost:"+ port +"/api/counseling/filter", filter, CounselingCenter[].class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), arrayContainingInAnyOrder(
                CounselingCenter.builder()
                        .id("123")
                        .name("SEEHAUS Suchtberatungs- und Behandlungszentrum Wandsbek")
                        .address(Address.builder().street("Hasselbrookstraße 94a").postalCode("22089").city("Hamburg").build())
                        .phoneNo("040 2000102000")
                        .email("info@seehaus-hh.de")
                        .url("http://www.therapiehilfe.de")
                        .specializations(List.of("Suchtberatung", "Vermittlung von Selbsthilfegruppen", "Gruppenarbeit", "Krisenintervention"))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                        .build()));
    }
}