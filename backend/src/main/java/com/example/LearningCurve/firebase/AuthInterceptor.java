package com.example.LearningCurve.firebase;

import com.example.LearningCurve.session.SessionService;
import com.example.LearningCurve.user.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    SessionService sessionService;

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        List<String> authorization = request.getHeaders().get("Authorization");

        if (authorization == null) {
            return chain.next(request);
        }

        Optional<String> tokenId = authorization.stream().findFirst().map(token -> token.split(" ")[1]);
        Optional<UserId> user = tokenId.map(FirebaseUtils::getUidFromToken).map(UserId::new);
        user.ifPresent(sessionService::maybeUpdateSession);

        return chain.next(request);
    }
}