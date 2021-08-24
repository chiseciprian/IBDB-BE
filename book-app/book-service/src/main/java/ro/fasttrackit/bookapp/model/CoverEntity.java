package ro.fasttrackit.bookapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("cover")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoverEntity {
    @Id
    private String coverId;
    private String title;
    private Binary image;
}
