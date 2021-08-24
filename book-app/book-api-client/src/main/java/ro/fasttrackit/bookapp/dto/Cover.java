package ro.fasttrackit.bookapp.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import org.bson.types.Binary;

@Value
@Builder
@JsonDeserialize(builder = Cover.CoverBuilder.class)
public class Cover {
    private String coverId;
    private String title;
    private Binary image;

    @JsonPOJOBuilder(withPrefix = "")
    public static class CoverBuilder {
    }
}
