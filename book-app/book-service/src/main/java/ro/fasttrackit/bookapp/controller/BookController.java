package ro.fasttrackit.bookapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.fasttrackit.bookapp.dto.Book;
import ro.fasttrackit.bookapp.dto.Cover;
import ro.fasttrackit.bookapp.dto.BookFile;
import ro.fasttrackit.bookapp.model.mappers.BookMappers;
import ro.fasttrackit.bookapp.model.mappers.CoverMappers;
import ro.fasttrackit.bookapp.model.mappers.BookFileMappers;
import ro.fasttrackit.bookapp.service.BookService;
import ro.fasttrackit.bookapp.service.CoverService;
import ro.fasttrackit.bookapp.service.BookFileService;
import ro.fasttrackit.exceptions.ResourceNotFoundException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final CoverService coverService;
    private final BookFileService bookFileService;
    private final BookMappers bookMappers;
    private final CoverMappers coverMappers;

    private final BookFileMappers bookFileMappers;

    @GetMapping
    List<Book> getBooks() {
        return bookMappers.toApi(bookService.getBooks());
    }

    @GetMapping("{bookId}")
    Book getBookById(@PathVariable String bookId) {
        return bookService.getBookById(bookId)
                .map(bookMappers::toApi)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + bookId + " is not found"));
    }

    @GetMapping("/read-list")
    List<Book> getBooksAddedToReadList(@RequestParam String username) {
        return bookMappers.toApi(bookService.getBooksAddedToReadList(username));
    }

    @GetMapping("/my-books")
    List<Book> getPurchasedBooks(@RequestParam String username) {
        return bookMappers.toApi(bookService.getPurchasedBooks(username));
    }

    @GetMapping("/author")
    List<Book> getBooksByAuthorUsername(@RequestParam String username) {
        return bookMappers.toApi(bookService.getBooksByAuthorUsername(username));
    }

    @PostMapping
    Book addBook(@RequestBody Book book) {
        return bookMappers.toApi(bookService.addBook(bookMappers.toDb(book)));
    }

    @PostMapping("/buy")
    void buyBook(@RequestParam String username, @RequestParam String bookId) {
        bookService.buyBook(username, bookId);
    }

    @PutMapping
    Book updateBook(@RequestBody Book book) {
        return bookMappers.toApi(bookService.updateBook(bookMappers.toDb(book)));
    }

    @DeleteMapping("{bookId}")
    void deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(bookId);
    }

    @PostMapping("/cover/add")
    public Cover addCover(@RequestParam("title") String title, @RequestParam("image") MultipartFile image) throws IOException {
        return coverMappers.toApi(coverService.addCover(title, image));
    }

    @GetMapping("/cover/{coverId}")
    public Cover getCover(@PathVariable String coverId) {
        return coverService.getCover(coverId)
                .map(coverMappers::toApi)
                .orElseThrow(() -> new ResourceNotFoundException("Cover with id " + coverId + " is not found"));
    }

    @GetMapping("/file/{fileId}")
    public BookFile getBookFile(@PathVariable String fileId) {
        return bookFileService.getBookFile(fileId)
                .map(bookFileMappers::toApi)
                .orElseThrow(() -> new ResourceNotFoundException("Book File with id " + fileId + " is not found"));
    }
}
