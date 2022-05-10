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

    public List<BookEntity> getBooksAddedToReadList() {
        return repository.getAllByAddedToReadListTrue();
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

    public BookEntity updateBook(BookEntity updatedBook) {
        BookEntity dbBook = repository.findById(updatedBook.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + updatedBook.getBookId() + " is not found"));
        validator.validateBook(dbBook);
        replaceBook(dbBook, updatedBook);
        return repository.save(dbBook);
    }

    private void replaceBook(BookEntity dbBook, BookEntity updatedBook) {
        dbBook.setTitle(updatedBook.getTitle());
        dbBook.setDescription(updatedBook.getDescription());
        dbBook.setAuthors(updatedBook.getAuthors());
        dbBook.setGenres(updatedBook.getGenres());
        dbBook.setCoverId(updatedBook.getCoverId());
        dbBook.setFileId(updatedBook.getFileId());
        dbBook.setAddedToReadList(updatedBook.isAddedToReadList());
    }
}
