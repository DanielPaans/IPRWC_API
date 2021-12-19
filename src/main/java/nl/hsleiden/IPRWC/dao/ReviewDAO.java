package nl.hsleiden.IPRWC.dao;

import nl.hsleiden.IPRWC.models.Review;
import nl.hsleiden.IPRWC.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewDAO {

    private final ReviewRepository REVIEW_REPOSITORY;

    public ReviewDAO(ReviewRepository reviewRepository) {
        REVIEW_REPOSITORY = reviewRepository;
    }

    public List<Review> getReviewsByUserId(UUID userId) {
        return REVIEW_REPOSITORY.getReviewsByUserId(userId);
    }

    public List<Review> getReviews() {
        return REVIEW_REPOSITORY.findAll();
    }

    public Review storeReview(Review review) {
        return REVIEW_REPOSITORY.save(review);
    }

    public void changeDescription(UUID id, String description) {
        REVIEW_REPOSITORY.updateReviewDescription(id, description);
    }

    public void changeRating(UUID id, int rating) {
        REVIEW_REPOSITORY.updateReviewRating(id, rating);
    }
}
