package ro.fasttrackit.ratingapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.ratingapp.model.RatingEntity;
import ro.fasttrackit.ratingapp.repository.RatingRepository;

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
        return repository.save(newRating);
    }

    public void deleteRating(String ratingId) {
        repository.deleteById(ratingId);
    }
}
