package ro.fasttrackit.bookapp.model.mappers;

import org.springframework.stereotype.Component;
import ro.fasttrackit.bookapp.dto.Cover;
import ro.fasttrackit.bookapp.model.CoverEntity;
import ro.fasttrackit.utils.ModelMappers;

@Component
public class CoverMappers implements ModelMappers<Cover, CoverEntity> {
    @Override
    public Cover toApi(CoverEntity source) {
        return Cover.builder()
                .coverId(source.getCoverId())
                .title(source.getTitle())
                .image(source.getImage())
                .build();
    }

    @Override
    public CoverEntity toDb(Cover source) {
        return CoverEntity.builder()
                .coverId(source.getCoverId())
                .title(source.getTitle())
                .image(source.getImage())
                .build();
    }
}
