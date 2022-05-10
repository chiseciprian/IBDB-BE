package ro.fasttrackit.bookapp.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import org.bson.types.Binary;

import java.io.InputStream;

@Value
@Builder
@JsonDeserialize(builder = File.FileBuilder.class)
public class File {
    private String fileId;
    private String title;
    private InputStream file;

    @JsonPOJOBuilder(withPrefix = "")
    public static class FileBuilder {
    }
}
