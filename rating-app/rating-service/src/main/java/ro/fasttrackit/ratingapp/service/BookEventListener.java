package ro.fasttrackit.ratingapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ro.fasttrackit.bookapp.events.BookEvent;
import ro.fasttrackit.bookapp.events.BookEventType;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookEventListener {
    private final RatingService ratingService;

    @RabbitListener(queues = "#{bookQueue.name}")
    void processBookEvent(BookEvent event) {
        if (event.getType() == BookEventType.DELETED) {
            ratingService.deleteAllRatingsByBookId(event.getBookId());
            log.info("Deleted all ratings for bookId" + event.getBookId());
        }
    }
}
