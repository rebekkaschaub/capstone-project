package de.neuefische.backend.dto;

import de.neuefische.backend.model.Address;
import de.neuefische.backend.model.CounselingSetting;
import de.neuefische.backend.model.TargetGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CounselingCenterQueryDto {
    private String city;
    private String postalCode;
    //private int distance;
    private String specialization;
    private List<TargetGroup> targetGroup;
    private List<CounselingSetting> counselingSetting;
}
