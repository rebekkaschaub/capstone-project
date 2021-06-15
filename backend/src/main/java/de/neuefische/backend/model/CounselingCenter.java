package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CounselingCenter {
    private String name;
    private Address address;
    private String phoneNo;
    private String email;
    private String url;
    private List<String> specializations;
    private List<TargetGroup> targetGroup;
    private List<CounselingSetting> counselingSetting;
}
