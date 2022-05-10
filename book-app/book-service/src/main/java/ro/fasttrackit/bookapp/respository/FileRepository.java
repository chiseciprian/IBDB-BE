package ro.fasttrackit.bookapp.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.bookapp.model.FileEntity;

public interface FileRepository extends MongoRepository<FileEntity, String> {
}
