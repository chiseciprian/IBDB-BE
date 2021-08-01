package ro.fasttrackit.ratingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fasttrackit.ratingapp.dto.Rating;
import ro.fasttrackit.ratingapp.model.mapper.RatingMappers;
import ro.fasttrackit.ratingapp.service.RatingService;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;
    private final RatingMappers mappers;

    @GetMapping("book/{bookId}")
    List<Rating> getRatingsByBookId(@PathVariable String bookId) {
        return mappers.toApi(ratingService.getRatings(bookId));
    }
}
