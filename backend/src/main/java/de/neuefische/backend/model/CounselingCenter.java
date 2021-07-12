package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "counselingCenter")
public class CounselingCenter {
    @Id
    private String id;
    private String name;
    private Address address;
    private String phoneNo;
    private String email;
    private String url;
    private List<Specialization> specializations;
    private List<TargetGroup> targetGroup;
    private List<CounselingSetting> counselingSetting;
    private boolean supportGroups;
    private List<String> bookmarkedBy;
}
