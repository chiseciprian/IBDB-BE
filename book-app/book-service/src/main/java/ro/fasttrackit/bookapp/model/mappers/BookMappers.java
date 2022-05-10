package ro.fasttrackit.bookapp.model.mappers;

import org.springframework.stereotype.Component;
import ro.fasttrackit.bookapp.dto.Book;
import ro.fasttrackit.bookapp.model.BookEntity;
import ro.fasttrackit.utils.ModelMappers;

@Component
public class BookMappers implements ModelMappers<Book, BookEntity> {

    public Book toApi(BookEntity source) {
        return Book.builder()
                .bookId(source.getBookId())
                .title(source.getTitle())
                .description(source.getDescription())
                .price(source.getPrice())
                .authors(source.getAuthors())
                .users(source.getUsers())
                .genres(source.getGenres())
                .coverId(source.getCoverId())
                .fileId(source.getFileId())
                .addedToReadList(source.getAddedToReadList())
                .build();
    }

    public BookEntity toDb(Book source) {
        return BookEntity.builder()
                .bookId(source.getBookId())
                .title(source.getTitle())
                .description(source.getDescription())
                .price(source.getPrice())
                .authors(source.getAuthors())
                .users(source.getUsers())
                .genres(source.getGenres())
                .coverId(source.getCoverId())
                .addedToReadList(source.getAddedToReadList())
                .fileId(source.getFileId())
                .build();
    }
}
