package ro.fasttrackit.bookapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.bookapp.model.BookEntity;
import ro.fasttrackit.bookapp.model.BookFileEntity;
import ro.fasttrackit.bookapp.respository.BookFileRepository;
import ro.fasttrackit.bookapp.respository.BookRepository;
import ro.fasttrackit.bookapp.respository.CoverRepository;
import ro.fasttrackit.bookapp.service.validator.BookValidator;
import ro.fasttrackit.bookapp.utility.PdfGenerator;
import ro.fasttrackit.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final BookNotifications bookNotifications;
    private final BookValidator validator;
    private final BookFileRepository bookFileRepository;
    private final CoverRepository coverRepository;
    private final PdfGenerator pdfGenerator;

    public List<BookEntity> getBooks() {
        return repository.findAll();
    }

    public List<BookEntity> getBooksAddedToReadList(String username) {
        return repository.findAll().stream()
                .filter(bookEntity -> bookEntity.getAddedToReadList().contains(username))
                .collect(Collectors.toList());
    }

    public List<BookEntity> getPurchasedBooks(String username) {
        return repository.findAll().stream()
                .filter(bookEntity -> bookEntity.getUsers().contains(username))
                .collect(Collectors.toList());
    }

    public List<BookEntity> getBooksByAuthorUsername(String username) {
        return repository.findAllByAuthorUsername(username);
    }

    public Optional<BookEntity> getBookById(String bookId) {
        return repository.findById(bookId);
    }

    public BookEntity addBook(BookEntity newBook) {
        newBook.setBookId(null);
        validator.validateBook(newBook);
        BookFileEntity result = pdfGenerator.pdfGenerator(newBook);
        newBook.setFileId(result.getFileId());
        return repository.save(newBook);
    }

    public void buyBook(String username, String bookId) {
        BookEntity book = repository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + bookId + " is not found"));
        addUserToBook(username, book);
    }

    public void deleteBook(String bookId) {
        BookEntity book = repository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + bookId + " is not found"));
        coverRepository.deleteByCoverId(book.getCoverId());
        bookFileRepository.deleteByFileId(book.getFileId());
        repository.delete(book);
        bookNotifications.notifyBookDeleted(bookId);
    }

    public BookEntity updateBook(BookEntity updatedBook) {
        BookEntity dbBook = repository.findById(updatedBook.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + updatedBook.getBookId() + " is not found"));
        validator.validateBook(dbBook);
        replaceBook(dbBook, updatedBook);
        bookFileRepository.deleteByFileId(dbBook.getFileId());
        BookFileEntity result = pdfGenerator.pdfGenerator(updatedBook);
        dbBook.setFileId(result.getFileId());
        return repository.save(dbBook);
    }

    private void replaceBook(BookEntity dbBook, BookEntity updatedBook) {
        dbBook.setTitle(updatedBook.getTitle());
        dbBook.setBookText(updatedBook.getBookText());
        dbBook.setDescription(updatedBook.getDescription());
        dbBook.setPrice(updatedBook.getPrice());
        dbBook.setUsers(updatedBook.getUsers());
        dbBook.setGenres(updatedBook.getGenres());
        dbBook.setCoverId(updatedBook.getCoverId());
        dbBook.setFileId(updatedBook.getFileId());
        dbBook.setAddedToReadList(updatedBook.getAddedToReadList());
    }

    private void addUserToBook(String username, BookEntity book) {
        List<String> users = book.getUsers();
        if (!users.contains(username)) {
            users.add(username);
            book.setUsers(users);
            updateBook(book);
        }
    }
}
