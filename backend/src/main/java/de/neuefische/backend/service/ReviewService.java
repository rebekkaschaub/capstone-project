package de.neuefische.backend.service;

import de.neuefische.backend.dto.ReviewDto;
import de.neuefische.backend.model.Review;
import de.neuefische.backend.repos.ReviewRepo;
import de.neuefische.backend.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepo repo;
    private final IdUtils idUtils;

    @Autowired
    public ReviewService(ReviewRepo repo, IdUtils idUtils) {
        this.repo = repo;
        this.idUtils = idUtils;
    }

    public List<Review> listAllReviewsOfUser(String name) {
        return repo.findByAuthor(name);
    }

    public List<Review> listAllReviewsOfCounselingCenter(String counselingCenterId) {
        return repo.findByCounselingCenterId(counselingCenterId);
    }

    public Review addReview(ReviewDto reviewDto, String name) {
        Review review = Review.builder()
                .reviewId(idUtils.generateUuid())
                .counselingCenterId(reviewDto.getCounselingCenterId())
                .author(name)
                .stars(reviewDto.getStars())
                .comment(reviewDto.getComment()).build();

        return repo.save(review);
    }
}
