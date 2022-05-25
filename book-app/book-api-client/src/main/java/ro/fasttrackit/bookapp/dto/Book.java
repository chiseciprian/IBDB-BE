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
    private double price;
    private String authorUsername;
    private String authorName;
    private List<String> users;
    private List<Genre> genres;
    private String coverId;
    private List<String> addedToReadList;
    private String fileId;

    @JsonPOJOBuilder(withPrefix = "")
    public static class BookBuilder {
    }
}
