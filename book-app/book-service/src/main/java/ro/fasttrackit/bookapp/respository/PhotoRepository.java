package ro.fasttrackit.bookapp.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.bookapp.model.PhotoEntity;

public interface PhotoRepository extends MongoRepository<PhotoEntity, String> {
}
