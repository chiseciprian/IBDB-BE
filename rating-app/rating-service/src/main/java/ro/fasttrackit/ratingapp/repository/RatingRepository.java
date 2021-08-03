package ro.fasttrackit.ratingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.ratingapp.model.RatingEntity;

import java.util.List;

public interface RatingRepository extends MongoRepository<RatingEntity, String> {

    List<RatingEntity> findAllByBookId(String bookId);
}
