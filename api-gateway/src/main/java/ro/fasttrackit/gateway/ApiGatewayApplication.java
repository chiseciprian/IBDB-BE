package ro.fasttrackit.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("books", r -> r.path("/books")
                        .uri("http://localhost:9011/books"))
                .route("books", r -> r.path("/books/**")
                        .uri("http://localhost:9011/books/**"))
                .route("ratings", r -> r.path("/ratings")
                        .uri("http://localhost:9002/ratings"))
                .route("ratings", r -> r.path("/ratings/**")
                        .uri("http://localhost:9002/ratings/**"))

                .route("security", r -> r.path("/security")
                        .uri("http://localhost:9005/security"))
                .route("security", r -> r.path("/security/**")
                        .uri("http://localhost:9005/security/**"))

                .route("auth", r -> r.path("/auth")
                        .uri("http://localhost:9005/auth"))
                .route("auth", r -> r.path("/auth/**")
                        .uri("http://localhost:9005/auth/**"))


                .route("websocket_http_route", r -> r.path("/websocket/**")
                        .uri("http://localhost:9002"))
                .route("websocket_route", r -> r.path("/websocket")
                        .uri("ws://localhost:9002"))
                .build();
    }
}
