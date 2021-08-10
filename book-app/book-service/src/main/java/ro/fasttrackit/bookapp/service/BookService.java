package ro.fasttrackit.bookapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.bookapp.model.BookEntity;
import ro.fasttrackit.bookapp.respository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final BookNotifications bookNotifications;

    public List<BookEntity> getBooks() {
        return repository.findAll();
    }

    public Optional<BookEntity> getBookById(String bookId) {
        return repository.findById(bookId);
    }

    public BookEntity addBook(BookEntity newBook) {
        newBook.setBookId(null);
        return repository.save(newBook);
    }

    public void deleteBook(String bookId) {
        repository.deleteById(bookId);
        bookNotifications.notifyBookDeleted(bookId);
    }
}
