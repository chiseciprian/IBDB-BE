package ro.fasttrackit.securityservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ro.fasttrackit.exceptions.MissingAuthorizationException;
import ro.fasttrackit.securityapiclient.dto.TokenWrapperDto;
import ro.fasttrackit.securityservice.service.AuthorizationService;

import static org.springframework.security.web.server.ServerHttpBasicAuthenticationConverter.BASIC;


@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @GetMapping(value = "/auth/token")
    public Mono<TokenWrapperDto> getToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        if (authorization != null && authorization.startsWith(BASIC)) {
            return authorizationService.verifyCredentials(authorization);
        }
        return Mono
                .error(new MissingAuthorizationException("The resource you are trying to access needs authorization."));
    }
}
