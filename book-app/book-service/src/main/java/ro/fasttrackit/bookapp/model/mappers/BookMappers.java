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
                .authors(source.getAuthors())
                .genres(source.getGenres())
                .coverId(source.getCoverId())
                .build();
    }

    public BookEntity toDb(Book source) {
        return BookEntity.builder()
                .bookId(source.getBookId())
                .title(source.getTitle())
                .description(source.getDescription())
                .authors(source.getAuthors())
                .genres(source.getGenres())
                .coverId(source.getCoverId())
                .build();
    }
}
