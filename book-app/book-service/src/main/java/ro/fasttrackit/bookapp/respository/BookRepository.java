package ro.fasttrackit.bookapp.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.bookapp.model.BookEntity;

public interface BookRepository extends MongoRepository<BookEntity, String> {

    boolean findByTitle(String title);
}
