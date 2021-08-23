package ro.fasttrackit.bookapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.fasttrackit.bookapp.dto.Book;
import ro.fasttrackit.bookapp.model.PhotoEntity;
import ro.fasttrackit.bookapp.model.mappers.BookMappers;
import ro.fasttrackit.bookapp.service.BookService;
import ro.fasttrackit.bookapp.service.PhotoService;
import ro.fasttrackit.exceptions.ResourceNotFoundException;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final PhotoService photoService;
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

    @PutMapping
    Book updateBook(@RequestBody Book book) {
        return mappers.toApi(bookService.updateBook(mappers.toDb(book)));
    }

    @DeleteMapping("{bookId}")
    void deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(bookId);
    }

    @PostMapping("/cover/add")
    public PhotoEntity addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image) throws IOException {
        return photoService.addPhoto(title, image);
    }

    @GetMapping("/cover/{coverId}")
    public String getPhoto(@PathVariable String coverId) {
        PhotoEntity photo = photoService.getPhoto(coverId);
        return Base64.getEncoder().encodeToString(photo.getImage().getData());
    }
}
