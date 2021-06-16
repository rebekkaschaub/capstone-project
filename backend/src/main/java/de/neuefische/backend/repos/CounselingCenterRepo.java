package de.neuefische.backend.repos;

import de.neuefische.backend.model.Address;
import de.neuefische.backend.model.CounselingCenter;
import de.neuefische.backend.model.CounselingSetting;
import de.neuefische.backend.model.TargetGroup;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CounselingCenterRepo {

    private final List<CounselingCenter> counselingCenters = new ArrayList<>(List.of(
            CounselingCenter.builder()
                .name("SEEHAUS Suchtberatungs- und Behandlungszentrum Wandsbek")
                .address(Address.builder().street("Hasselbrookstraße 94a").postalCode("22089").city("Hamburg").build())
                .phoneNo("040 2000102000")
                .email("info@seehaus-hh.de")
                .url("http://www.therapiehilfe.de")
                .specializations(List.of("Suchtberatung", "Vermittlung von Selbsthilfegruppen", "Gruppenarbeit", "Krisenintervention"))
                .targetGroup(List.of(TargetGroup.INDIVIDUAL))
                .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE)).build(),
            CounselingCenter.builder()
                .name("Erziehungsberatungsstelle Billstedt")
                .address(Address.builder().street("Öjendorfer Weg 10a").postalCode("22111").city("Hamburg").build())
                .phoneNo("040 280140-620")
                .email("erziehungsberatung@caritas-hamburg.de")
                    .url("http://www.caritas-hamburg.de ")
                    .specializations(List.of("Erziehungsberatung", "Beratung für Kinder", "Jugendliche und Eltern (einschl. Beratung bei Trennung und Scheidung)" ))
                    .targetGroup(List.of(TargetGroup.INDIVIDUAL, TargetGroup.RELATIVES))
                    .counselingSetting(List.of(CounselingSetting.INPERSON, CounselingSetting.PHONE)).build()));

    public List<CounselingCenter> findAll() {
        return counselingCenters;
    }
}
