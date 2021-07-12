package de.neuefische.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private String counselingCenterId;
    private String counselingCenterName;
    private String title;
    private int rating;
    private String author;
    private String comment;
}
