package ro.fasttrackit.bookapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.bookapp.model.BookEntity;
import ro.fasttrackit.bookapp.respository.BookRepository;
import ro.fasttrackit.bookapp.service.validator.BookValidator;
import ro.fasttrackit.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final BookNotifications bookNotifications;
    private final BookValidator validator;

    public List<BookEntity> getBooks() {
        return repository.findAll();
    }

    public Optional<BookEntity> getBookById(String bookId) {
        return repository.findById(bookId);
    }

    public BookEntity addBook(BookEntity newBook) {
        newBook.setBookId(null);
        validator.validateBook(newBook);
        return repository.save(newBook);
    }

    public void deleteBook(String bookId) {
        BookEntity book = repository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + bookId + " is not found"));
        repository.delete(book);
        bookNotifications.notifyBookDeleted(bookId);
    }
}
