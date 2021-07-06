package de.neuefische.backend.controller;

import de.neuefische.backend.dto.ReviewDto;
import de.neuefische.backend.model.CounselingCenter;
import de.neuefische.backend.model.Review;
import de.neuefische.backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {

    private final ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public List<Review> listAllReviewsOfUser(Principal principal){
        return service.listAllReviewsOfUser(principal.getName());
    }

    @GetMapping("/{counselingCenterId}")
    public List<Review> listAllReviewsOfCounselingCenter(@PathVariable String counselingCenterId){
        return service.listAllReviewsOfCounselingCenter(counselingCenterId);
    }

    @PostMapping
    public Review addReview(@RequestBody ReviewDto reviewDto, Principal principal){
        if(!reviewDto.getAuthor().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return service.addReview(reviewDto);
    }

    @PutMapping("/{reviewId}")
    public Review updateReview(@PathVariable String reviewId,  @RequestBody ReviewDto reviewDto, Principal principal){
        if(!reviewDto.getAuthor().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        try {
            return service.updateReview(reviewId, reviewDto);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review with id does not exist: "+reviewId);
        }
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable String reviewId, Principal principal){
        service.deleteReview(reviewId, principal.getName());
    }
}
