package ro.fasttrackit.bookapp.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.bookapp.model.BookFileEntity;

public interface BookFileRepository extends MongoRepository<BookFileEntity, String> {
}
