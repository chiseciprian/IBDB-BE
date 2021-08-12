package ro.fasttrackit.ratingapp.service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ro.fasttrackit.bookapp.dto.Book;
import ro.fasttrackit.exceptions.ValidationException;
import ro.fasttrackit.ratingapp.model.RatingEntity;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class RatingValidator {
    private final String baseUrl = "http://localhost:8080";
    private final RestTemplate rest = new RestTemplate();

    public void validateRating(RatingEntity ratingEntity) {
        validate(ratingEntity).ifPresent(ex -> {
            throw ex;
        });
    }

    private Optional<ValidationException> validate(RatingEntity ratingEntity) {
        if (ratingEntity.getBookId() == null) {
            return Optional.of(new ValidationException("BookId cannot be null"));
        } else if (ratingEntity.getUserName() == null) {
            return Optional.of(new ValidationException("UserName cannot be null"));
        } else if (ratingEntity.getTitle() == null) {
            return Optional.of(new ValidationException("Title cannot be null"));
        } else if (ratingEntity.getMessage() == null) {
            return Optional.of(new ValidationException("Message cannot be null"));
        } else if (ratingEntity.getStars() == null) {
            return Optional.of(new ValidationException("Stars cannot be null"));
        } else if (ratingEntity.getStars() < 0) {
            return Optional.of(new ValidationException("Stars cannot be smaller than 0"));
        } else if (ratingEntity.getStars() > 5) {
            return Optional.of(new ValidationException("Stars cannot be higher than 5"));
        } else if (!checkBookExist(ratingEntity.getBookId())) {
            return Optional.of(new ValidationException("Book with id " + ratingEntity.getBookId() + " doesn't exist"));
        } else {
            return empty();
        }
    }

    private boolean checkBookExist(String bookId) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/books/")
                .path(bookId)
                .toUriString();

        try {
            Optional<Book> book = ofNullable(rest.getForObject(url, Book.class));
            return book.isPresent();
        } catch (HttpServerErrorException ex) {
            return false;
        }
    }
}
