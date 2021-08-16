package ro.fasttrackit.ratingapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.fasttrackit.exceptions.ResourceNotFoundException;
import ro.fasttrackit.ratingapp.model.RatingEntity;
import ro.fasttrackit.ratingapp.repository.RatingRepository;
import ro.fasttrackit.ratingapp.service.validator.RatingValidator;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository repository;
    private final RatingValidator ratingValidator;
    private final SimpMessagingTemplate messagingTemplate;

    public List<RatingEntity> getRatings(String bookId) {
        return repository.findAllByBookId(bookId);
    }

    public RatingEntity addRating(RatingEntity newRating) {
        newRating.setRatingId(null);
        newRating.setDate(new Timestamp(System.currentTimeMillis()).getTime());
        ratingValidator.validateRating(newRating);
        messagingTemplate.convertAndSend("rating-service", "rating-created");
        return repository.save(newRating);
    }

    public void deleteRating(String ratingId) {
        RatingEntity ratingEntity = repository.findById(ratingId)
                .orElseThrow(() -> new ResourceNotFoundException("Rating with id " + ratingId + " is not found"));
        messagingTemplate.convertAndSend("rating-service", "rating-deleted");
        repository.delete(ratingEntity);
    }

    public RatingEntity updateRating(RatingEntity updatedRating) {
        RatingEntity dbEntity = repository.findById(updatedRating.getRatingId())
                .orElseThrow(() -> new ResourceNotFoundException("Rating with id " + updatedRating.getRatingId() + " is not found"));
        ratingValidator.validateRating(dbEntity);
        replaceRating(dbEntity, updatedRating);
        return repository.save(dbEntity);
    }

    @Transactional
    public void deleteAllRatingsByBookId(String bookId) {
        repository.deleteAllByBookId(bookId);
    }

    private void replaceRating(RatingEntity dbEntity, RatingEntity updatedRating) {
        dbEntity.setUserName(updatedRating.getUserName());
        dbEntity.setTitle(updatedRating.getTitle());
        dbEntity.setMessage(updatedRating.getMessage());
        dbEntity.setDate(new Timestamp(System.currentTimeMillis()).getTime());
        dbEntity.setStars(updatedRating.getStars());
    }
}
