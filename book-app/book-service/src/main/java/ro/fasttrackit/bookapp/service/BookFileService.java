package ro.fasttrackit.bookapp.service;

import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.fasttrackit.bookapp.model.BookFileEntity;
import ro.fasttrackit.bookapp.model.CoverEntity;
import ro.fasttrackit.bookapp.respository.BookFileRepository;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookFileService {

    private final BookFileRepository bookFileRepository;

    public BookFileEntity addBookFile(String title, MultipartFile file) throws IOException {
        BookFileEntity cover = new BookFileEntity();
        cover.setTitle(title);
        cover.setBookFile(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        return bookFileRepository.save(cover);
    }

    public Optional<BookFileEntity> getBookFile(String id) {
        return bookFileRepository.findById(id);
    }
}
