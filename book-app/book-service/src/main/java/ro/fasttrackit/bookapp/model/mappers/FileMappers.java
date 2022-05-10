package ro.fasttrackit.bookapp.model.mappers;

import org.springframework.stereotype.Component;
import ro.fasttrackit.bookapp.dto.File;
import ro.fasttrackit.bookapp.model.FileEntity;
import ro.fasttrackit.utils.ModelMappers;

@Component
public class FileMappers implements ModelMappers<File, FileEntity> {

    @Override
    public File toApi(FileEntity source) {
        return File.builder()
                .fileId(source.getFileId())
                .title(source.getTitle())
                .file(source.getFile())
                .build();
    }

    @Override
    public FileEntity toDb(File source) {
        return FileEntity.builder()
                .fileId(source.getFileId())
                .title(source.getTitle())
                .file(source.getFile())
                .build();
    }
}
