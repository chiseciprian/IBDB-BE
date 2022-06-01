package ro.fasttrackit.bookapp.service;

import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.fasttrackit.bookapp.model.BookFileEntity;
import ro.fasttrackit.bookapp.respository.BookFileRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookFileService {

    private final BookFileRepository bookFileRepository;


    public BookFileEntity addBookFile(String title, ByteArrayOutputStream file)  {
        BookFileEntity fileEntity = new BookFileEntity();
        fileEntity.setTitle(title);
        fileEntity.setBookFile(new Binary(BsonBinarySubType.BINARY, file.toByteArray()));
        return bookFileRepository.save(fileEntity);
    }

    public Optional<BookFileEntity> getBookFile(String id) {
        return bookFileRepository.findById(id);
    }
}
