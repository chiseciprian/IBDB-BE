package ro.fasttrackit.bookapp.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import org.bson.types.Binary;

@Value
@Builder
@JsonDeserialize(builder = BookFile.BookFileBuilder.class)
public class BookFile {
    private String fileId;
    private String title;
    private Binary bookFile;

    @JsonPOJOBuilder(withPrefix = "")
    public static class BookFileBuilder {
    }
}
