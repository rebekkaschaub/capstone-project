package de.neuefische.backend.service;

import de.neuefische.backend.dto.ReviewDto;
import de.neuefische.backend.model.Review;
import de.neuefische.backend.repos.ReviewRepo;
import de.neuefische.backend.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    public Review addReview(ReviewDto reviewDto) {
       if(repo.existsByCounselingCenterIdAndAuthor(reviewDto.getCounselingCenterId(), reviewDto.getAuthor())){
           throw new ResponseStatusException(HttpStatus.CONFLICT, "Review already exists");
       }
        Review review = Review.builder()
                .reviewId(idUtils.generateUuid())
                .counselingCenterId(reviewDto.getCounselingCenterId())
                .counselingCenterName(reviewDto.getCounselingCenterName())
                .author(reviewDto.getAuthor())
                .title(reviewDto.getTitle())
                .rating(reviewDto.getRating())
                .comment(reviewDto.getComment()).build();

        return repo.save(review);
    }

    public void deleteReview(String reviewId, String username) {
       Optional<Review> reviewToDelete= repo.findById(reviewId);
       if(reviewToDelete.isEmpty()){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review with id does not exist: "+reviewId);
       }
       if(!reviewToDelete.get().getAuthor().equals(username)){
           throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Author and user do not match");
       }
        repo.deleteById(reviewId);
    }

    public Review updateReview(String reviewId, ReviewDto reviewDto) {
        if(!repo.existsById(reviewId)){
            throw new IllegalArgumentException();
        }
        Review review = Review.builder()
                .reviewId(reviewId)
                .counselingCenterId(reviewDto.getCounselingCenterId())
                .counselingCenterName(reviewDto.getCounselingCenterName())
                .author(reviewDto.getAuthor())
                .title(reviewDto.getTitle())
                .rating(reviewDto.getRating())
                .comment(reviewDto.getComment()).build();
        return repo.save(review);
    }
}
