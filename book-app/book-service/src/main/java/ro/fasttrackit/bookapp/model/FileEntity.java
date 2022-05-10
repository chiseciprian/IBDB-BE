package ro.fasttrackit.bookapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.InputStream;

@Document("file")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    @Id
    private String fileId;
    private String title;
    private InputStream file;
}
