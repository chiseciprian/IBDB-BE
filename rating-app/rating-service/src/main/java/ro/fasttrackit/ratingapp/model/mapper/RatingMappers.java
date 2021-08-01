package ro.fasttrackit.ratingapp.model.mapper;

import org.springframework.stereotype.Component;
import ro.fasttrackit.ratingapp.dto.Rating;
import ro.fasttrackit.ratingapp.model.RatingEntity;
import ro.fasttrackit.utils.ModelMappers;

@Component
public class RatingMappers implements ModelMappers<Rating, RatingEntity> {

    public Rating toApi(RatingEntity source) {
        return Rating.builder()
                .ratingId(source.getRatingId())
                .bookId(source.getBookId())
                .userName(source.getUserName())
                .title(source.getTitle())
                .message(source.getMessage())
                .date(source.getDate())
                .stars(source.getStars())
                .build();
    }

    public RatingEntity toDb(Rating source) {
        return RatingEntity.builder()
                .ratingId(source.getRatingId())
                .bookId(source.getBookId())
                .userName(source.getUserName())
                .title(source.getTitle())
                .message(source.getMessage())
                .date(source.getDate())
                .stars(source.getStars())
                .build();
    }
}
