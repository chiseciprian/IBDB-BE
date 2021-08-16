package ro.fasttrackit.ratingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/average/book/{bookId}")
    Double getRatingAverageByBookId(@PathVariable String bookId) {
        return ratingService.getRatingAverageByBookId(bookId);
    }

    @PostMapping
    Rating addRating(@RequestBody Rating rating) {
        return mappers.toApi(ratingService.addRating(mappers.toDb(rating)));

    }

    @DeleteMapping("/{ratingId}")
    void deleteRating(@PathVariable String ratingId) {
        ratingService.deleteRating(ratingId);
    }
}
