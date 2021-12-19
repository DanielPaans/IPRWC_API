package nl.hsleiden.IPRWC.controllers;

import jdk.jfr.consumer.RecordedEvent;
import nl.hsleiden.IPRWC.dao.ReviewDAO;
import nl.hsleiden.IPRWC.httpResponses.Response;
import nl.hsleiden.IPRWC.models.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("${DEFAULT_PATH}${REVIEW}")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private final ReviewDAO REVIEW_DAO;

    public ReviewController(ReviewDAO reviewDAO) {
        REVIEW_DAO = reviewDAO;
    }

    @GetMapping
    public List<Review> getReviews(@RequestParam(value = "user") Optional<UUID> userId) {
        if(userId.isPresent()) {
            return REVIEW_DAO.getReviewsByUserId(userId.get());
        } else {
            return REVIEW_DAO.getReviews();
        }
    }

    @PostMapping
    public Review postReview(@RequestBody Review review) {
        return REVIEW_DAO.storeReview(review);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> editReview(@PathVariable UUID id,
                                               @RequestParam("description") Optional<String> description,
                                               @RequestParam("rating") Optional<Integer> rating) {
        description.ifPresent(s -> REVIEW_DAO.changeDescription(id, s));
        rating.ifPresent(s -> REVIEW_DAO.changeRating(id, s));

        return ResponseEntity.ok(new Response("review updated"));
    }

}
