package ro.fasttrackit.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("security-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://security-service"))
                .route("books", r -> r.path("/books")
                        .filters(f -> f.filter(filter))
                        .uri("lb://book-service"))
                .route("books", r -> r.path("/books/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://book-service"))
                .route("ratings", r -> r.path("/ratings")
                        .uri("http://localhost:9002/ratings"))
                .route("ratings", r -> r.path("/ratings/**")
                        .uri("http://localhost:9002/ratings/**"))
                .route("websocket_http_route", r -> r.path("/websocket/**")
                        .uri("http://localhost:9002"))
                .route("websocket_route", r -> r.path("/websocket")
                        .uri("ws://localhost:9002"))
                .build();
    }

}
