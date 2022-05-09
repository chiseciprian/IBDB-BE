package ro.fasttrackit.bookapp.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.bookapp.model.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<BookEntity, String> {
    Optional<BookEntity> findByTitle(String title);
}
