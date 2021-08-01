package ro.fasttrackit.ratingapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.ratingapp.model.RatingEntity;

public interface RatingRepository extends MongoRepository<RatingEntity, String> {
}
