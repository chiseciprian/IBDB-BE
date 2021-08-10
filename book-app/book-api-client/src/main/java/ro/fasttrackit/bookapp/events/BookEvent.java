package ro.fasttrackit.bookapp.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = BookEvent.BookEventBuilder.class)
public class BookEvent {
    String bookId;
    BookEventType type;

    @JsonPOJOBuilder(withPrefix = "")
    public static class BookEventBuilder {
    }
}
