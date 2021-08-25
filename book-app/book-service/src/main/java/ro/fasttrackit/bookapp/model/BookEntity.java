package ro.fasttrackit.bookapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.fasttrackit.bookapp.dto.Genre;

import java.util.List;

@Document("book")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {
    @Id
    private String bookId;
    private String title;
    private String description;
    private List<String> authors;
    private List<Genre> genres;
    private String coverId;
    private boolean addedToReadList;
}
