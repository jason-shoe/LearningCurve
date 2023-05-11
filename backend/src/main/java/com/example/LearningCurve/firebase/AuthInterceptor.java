package com.example.LearningCurve.firebase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Component
class AuthInterceptor implements WebGraphQlInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        logger.info(request.toString());
        List<String> authorization = request.getHeaders().get("Authorization");
        
        if (authorization == null) {
            return chain.next(request);
        }

        Optional<String> tokenId = authorization.stream().findFirst().map(token -> token.split(" ")[1]);
        Optional<String> user = tokenId.map(FirebaseUtils::getUidFromToken);
        user.ifPresent(logger::info);
        return chain.next(request);
    }
}