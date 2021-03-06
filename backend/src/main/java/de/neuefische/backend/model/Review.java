package de.neuefische.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "reviews")
public class Review {

    @Id
    private String reviewId;
    private String counselingCenterId;
    private String counselingCenterName;
    private String author;
    private String title;
    private int rating;
    private String comment;

}
