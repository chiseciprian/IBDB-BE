package ro.fasttrackit.bookapp.model.mappers;

import org.springframework.stereotype.Component;
import ro.fasttrackit.bookapp.dto.BookFile;
import ro.fasttrackit.bookapp.model.BookFileEntity;
import ro.fasttrackit.utils.ModelMappers;

@Component
public class BookFileMappers implements ModelMappers<BookFile, BookFileEntity> {

    @Override
    public BookFile toApi(BookFileEntity source) {
        return BookFile.builder()
                .fileId(source.getFileId())
                .title(source.getTitle())
                .bookFile(source.getBookFile())
                .build();
    }

    @Override
    public BookFileEntity toDb(BookFile source) {
        return BookFileEntity.builder()
                .fileId(source.getFileId())
                .title(source.getTitle())
                .bookFile(source.getBookFile())
                .build();
    }
}
