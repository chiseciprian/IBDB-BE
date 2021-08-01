package ro.fasttrackit.bookapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.bookapp.dto.Book;
import ro.fasttrackit.bookapp.model.mappers.BookMappers;
import ro.fasttrackit.bookapp.service.BookService;
import ro.fasttrackit.exceptions.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookMappers mappers;

    @GetMapping
    List<Book> getBooks() {
        return mappers.toApi(bookService.getBooks());
    }

    @GetMapping("{bookId}")
    Book getBookById(@PathVariable String bookId) {
        return bookService.getBookById(bookId)
                .map(mappers::toApi)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + bookId + " is not found"));
    }

    @PostMapping
    Book addBook(@RequestBody Book book) {
        return mappers.toApi(bookService.addBook(mappers.toDb(book)));

    }

    @DeleteMapping
    void deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(bookId);
    }
}
