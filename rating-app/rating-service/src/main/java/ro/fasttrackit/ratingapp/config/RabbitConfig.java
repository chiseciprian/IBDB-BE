package ro.fasttrackit.ratingapp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    FanoutExchange bookExchange() {
        return new FanoutExchange("books.exchange");
    }

    @Bean
    Queue bookQueue() {
        return new AnonymousQueue();
    }

    @Bean
    Binding binding(Queue bookQueue, FanoutExchange bookExchange) {
        return BindingBuilder.bind(bookQueue).to(bookExchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
