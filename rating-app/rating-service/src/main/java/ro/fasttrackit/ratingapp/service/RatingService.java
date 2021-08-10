package ro.fasttrackit.ratingapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.fasttrackit.ratingapp.model.RatingEntity;
import ro.fasttrackit.ratingapp.repository.RatingRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository repository;

    public List<RatingEntity> getRatings(String bookId) {
        return repository.findAllByBookId(bookId);
    }

    public RatingEntity addRating(RatingEntity newRating) {
        newRating.setRatingId(null);
        newRating.setDate(new Timestamp(System.currentTimeMillis()).getTime());
        return repository.save(newRating);
    }

    public void deleteRating(String ratingId) {
        repository.deleteById(ratingId);
    }

    @Transactional
    public void deleteAllRatingsByBookId(String bookId) {
        repository.deleteAllByBookId(bookId);
    }
}
