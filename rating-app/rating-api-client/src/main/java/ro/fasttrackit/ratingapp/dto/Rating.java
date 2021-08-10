package ro.fasttrackit.ratingapp.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Rating.RatingBuilder.class)
public class Rating {
    private String ratingId;
    private String bookId;
    private String userName;
    private String title;
    private String message;
    private long date;
    private Integer stars;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RatingBuilder {
    }
}
