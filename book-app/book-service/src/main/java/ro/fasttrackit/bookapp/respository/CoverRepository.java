package ro.fasttrackit.bookapp.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.bookapp.model.CoverEntity;

public interface CoverRepository extends MongoRepository<CoverEntity, String> {

    void deleteByCoverId(String coverId);
}
