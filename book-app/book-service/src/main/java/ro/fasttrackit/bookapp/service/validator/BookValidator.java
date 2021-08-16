package ro.fasttrackit.bookapp.service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.exceptions.ValidationException;
import ro.fasttrackit.bookapp.model.BookEntity;
import ro.fasttrackit.bookapp.respository.BookRepository;

import java.util.Optional;

import static java.util.Optional.empty;

@Component
@RequiredArgsConstructor
public class BookValidator {
    private final BookRepository bookRepository;

    public void validateBook(BookEntity bookEntity) {
        validate(bookEntity).ifPresent(ex -> {
            throw ex;
        });
    }

    private Optional<ValidationException> validate(BookEntity bookEntity) {
        if (bookEntity.getTitle() == null) {
            return Optional.of(new ValidationException("Title cannot be null"));
        } else if (bookRepository.findByTitle(bookEntity.getTitle()).isPresent()) {
            return Optional.of(new ValidationException("Book with this title already exist"));
        } else if (bookEntity.getDescription() == null) {
            return Optional.of(new ValidationException("Description cannot be null"));
        } else if (bookEntity.getAuthors().size() == 0) {
            return Optional.of(new ValidationException("Please add an author"));
        } else {
            return empty();
        }
    }
}
