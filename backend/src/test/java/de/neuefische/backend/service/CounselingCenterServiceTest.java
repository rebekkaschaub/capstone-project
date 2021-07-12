package de.neuefische.backend.service;

import de.neuefische.backend.model.*;
import de.neuefische.backend.repos.CounselingCenterRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class CounselingCenterServiceTest {


    private final MongoTemplate mongoTemplate = mock(MongoTemplate.class);
    private final CounselingCenterRepo repo = mock(CounselingCenterRepo.class);
    private final CounselingCenterService service = new CounselingCenterService(repo, mongoTemplate);

    @Test
    void filterCounselingCenter() {
        Query query = new Query();
        query.addCriteria(Criteria.where("address.city").is("Hamburg"));
        query.addCriteria(Criteria.where("address.postalCode").is("21109"));
        query.addCriteria(Criteria.where("specializations").in(Specialization.PSYCHISCH));
        query.addCriteria(Criteria.where("targetGroup").in(List.of(TargetGroup.INDIVIDUAL)));
        query.addCriteria(Criteria.where("counselingSetting").in(List.of(CounselingSetting.GROUP, CounselingSetting.INPERSON)));

        MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<>();
        queryMap.add("city", "Hamburg");
        queryMap.add("postalCode", "21109");
        queryMap.add("specialization", "PSYCHISCH");
        queryMap.add("targetGroup", "INDIVIDUAL");
        queryMap.add("counselingSetting", "GROUP");
        queryMap.add("counselingSetting", "INPERSON");

        when(mongoTemplate.find(query, CounselingCenter.class)).thenReturn(List.of(
                CounselingCenter.builder()
                        .id("777")
                        .name("Beste Beratung")
                        .address(Address.builder().street("Schokoladenstraße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("000")
                        .email("IloveChocolate@gmx.de")
                        .url("beste url")
                        .specializations(List.of(Specialization.ERZIEHUNGSBERATUNG, Specialization.PSYCHISCH, Specialization.ALLEINERZIEHENDE))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                        .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                        .supportGroups(true).build(),
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
                        .supportGroups(true).build(),
                CounselingCenter.builder()
                        .id("444")
                        .name("Super Beratung ")
                        .address(Address.builder().street("Super Straße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("11111")
                        .url("super url")
                        .specializations(List.of(Specialization.PSYCHISCH, Specialization.LEBENSBERATUNG, Specialization.LSBTIQ))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.GROUP, CounselingSetting.INPERSON))
                        .supportGroups(true).build()
        ));

        //WHEN
        List<CounselingCenter> actual = service.filterCounselingCenter(queryMap);

        //THEN
        verify(mongoTemplate).find(query, CounselingCenter.class);
        assertThat(actual, containsInAnyOrder(
                CounselingCenter.builder()
                        .id("777")
                        .name("Beste Beratung")
                        .address(Address.builder().street("Schokoladenstraße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("000")
                        .email("IloveChocolate@gmx.de")
                        .url("beste url")
                        .specializations(List.of(Specialization.ERZIEHUNGSBERATUNG, Specialization.PSYCHISCH, Specialization.ALLEINERZIEHENDE))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                        .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE))
                        .supportGroups(true).build(),
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
                        .supportGroups(true).build(),
                CounselingCenter.builder()
                        .id("444")
                        .name("Super Beratung ")
                        .address(Address.builder().street("Super Straße").postalCode("21109").city("Hamburg").build())
                        .phoneNo("11111")
                        .url("super url")
                        .specializations(List.of(Specialization.PSYCHISCH, Specialization.LEBENSBERATUNG, Specialization.LSBTIQ))
                        .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                        .counselingSetting(List.of(CounselingSetting.GROUP, CounselingSetting.INPERSON))
                        .supportGroups(true).build()
                )
        );
    }


    @Test
    @DisplayName("method addParamToQuery should add all Params from MulitValuemap to query")
    void addParamsWithValidParams() {
        //GIVEN
        Query actual = new Query();

        Query expected = new Query();
        expected.addCriteria(Criteria.where("address.city").is("Hamburg"));
        expected.addCriteria(Criteria.where("address.postalCode").is("21109"));
        expected.addCriteria(Criteria.where("specializations").in(Specialization.PSYCHISCH));
        expected.addCriteria(Criteria.where("targetGroup").in(List.of(TargetGroup.INDIVIDUAL)));
        expected.addCriteria(Criteria.where("counselingSetting").in(List.of(CounselingSetting.GROUP, CounselingSetting.INPERSON)));

        MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<>();
        queryMap.add("city", "Hamburg");
        queryMap.add("postalCode", "21109");
        queryMap.add("specialization", "PSYCHISCH");
        queryMap.add("targetGroup", "INDIVIDUAL");
        queryMap.add("counselingSetting", "GROUP");
        queryMap.add("counselingSetting", "INPERSON");


        //WHEN
        service.addParamsToQuery(queryMap, actual);

        //THEN
        assertThat(actual, is(expected));
    }

    @Test
    @DisplayName("method addParamToQuery should throw IllegalArgumentException, when requestParams are invalid")
    void addParamsWithInvalidParams() {
        //GIVEN
        Query actual = new Query();

        Query expected = new Query();
        expected.addCriteria(Criteria.where("address.city").is("Hamburg"));
        expected.addCriteria(Criteria.where("address.postalCode").is("21109"));
        expected.addCriteria(Criteria.where("specializations").in(Specialization.PSYCHISCH));
        expected.addCriteria(Criteria.where("targetGroup").in(List.of(TargetGroup.INDIVIDUAL)));
        expected.addCriteria(Criteria.where("counselingSetting").in(List.of(CounselingSetting.GROUP, CounselingSetting.INPERSON)));

        MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<>();
        queryMap.add("city", "Hamburg");
        queryMap.add("postalCode", "21109");
        queryMap.add("specialization", "PSYCHISCH");
        queryMap.add("targetGroup", "INDIVIDUAL");
        queryMap.add("counselingSetting", "GROUP");
        queryMap.add("counselingSetting", "NOTVALID");


        //WHEN/THEN
        assertThrows(IllegalArgumentException.class, () -> service.addParamsToQuery(queryMap, actual));
    }

}