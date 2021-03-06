package ro.fasttrackit.ratingapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("rating")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingEntity {
    @Id
    private String ratingId;
    private String bookId;
    private String userName;
    private String title;
    private String message;
    private long date;
    private Integer stars;
}
