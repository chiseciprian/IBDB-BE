package ro.fasttrackit.securityapiclient.application;

import reactor.core.publisher.Mono;
import ro.fasttrackit.securityapiclient.domain.response.TokenWrapperDto;

public interface AuthorizationServiceApi {

    public Mono<TokenWrapperDto> verifyCredentials(String authorization);
}
