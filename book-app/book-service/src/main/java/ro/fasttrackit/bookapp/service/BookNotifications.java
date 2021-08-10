package ro.fasttrackit.bookapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ro.fasttrackit.bookapp.events.BookEvent;
import ro.fasttrackit.bookapp.model.mappers.BookMappers;

import static ro.fasttrackit.bookapp.events.BookEventType.DELETED;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookNotifications {
    private final RabbitTemplate rabbit;
    private final BookMappers mapper;
    private final FanoutExchange bookExchange;

    public void notifyBookDeleted(String bookId) {
        BookEvent event = BookEvent.builder()
                .bookId(bookId)
                .type(DELETED)
                .build();
        log.info("Sending event : " + event);
        rabbit.convertAndSend(bookExchange.getName(), "", event);
    }
}
