package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CounselingCenter {
    private String name;
    private Address address;
    private String email;
    private String url;
    private List<String> specializations;
    private TargetGroup targetGroup;
    private CounselingSetting counselingSetting;
}
