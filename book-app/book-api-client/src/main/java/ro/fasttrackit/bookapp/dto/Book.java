package ro.fasttrackit.bookapp.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@JsonDeserialize(builder = Book.BookBuilder.class)
public class Book {
    private String bookId;
    private String title;
    private String description;
    private List<String> authors;
    private List<Genre> genres;
    private String coverId;

    @JsonPOJOBuilder(withPrefix = "")
    public static class BookBuilder {
    }
}
