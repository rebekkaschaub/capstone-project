package de.neuefische.backend.service;

import de.neuefische.backend.model.*;
import de.neuefische.backend.repos.CounselingCenterRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
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
        queryMap.add("city","Hamburg");
        queryMap.add("postalCode","21109");
        queryMap.add("specialization","PSYCHISCH");
        queryMap.add("targetGroup","INDIVIDUAL");
        queryMap.add("counselingSetting","GROUP");
        queryMap.add("counselingSetting","INPERSON");
        when(mongoTemplate.find(query, CounselingCenter.class)).thenReturn(List.of(
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
                        .specializations(List.of(Specialization.ERZIEHUNGSBERATUNG,Specialization.PSYCHISCH, Specialization.ALLEINERZIEHENDE))
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
}