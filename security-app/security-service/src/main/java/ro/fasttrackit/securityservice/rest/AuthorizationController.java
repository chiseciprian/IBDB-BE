package ro.fasttrackit.securityservice.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ro.fasttrackit.securityapiclient.domain.response.TokenWrapperDto;
import ro.fasttrackit.securityapiclient.exception.rest.MissingAuthorizationException;
import ro.fasttrackit.securityapiclient.rest.AuthorizationApi;
import ro.fasttrackit.securityservice.application.AuthorizationService;

import static org.springframework.security.web.server.ServerHttpBasicAuthenticationConverter.BASIC;


@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizationController implements AuthorizationApi {

    private final AuthorizationService authorizationService;

    @Override
    public Mono<TokenWrapperDto> getToken(String authorization) {
        if (authorization != null && authorization.startsWith(BASIC)) {
            return authorizationService.verifyCredentials(authorization);
        }
        return Mono
                .error(new MissingAuthorizationException("The resource you are trying to access needs authorization."));
    }
}
