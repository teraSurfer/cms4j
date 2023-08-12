package com.example.cms4j.article;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Controller
public class SubscriptionController {

    @QueryMapping
    public Mono<String> greeting() {
        return Mono.just("Hello!");
    }

    @SubscriptionMapping
    public Flux<String> notifyString() {
        log.info("listening for strings");
        // listen to so Pub/sub (eg. redis channels, kafka, queue, mongo change streams) stream,
        // any non blocking stream that returns a publisher.
        // and publish values based on stream results.
        return Mono.delay(Duration.ofMillis(50))
                .flatMapMany(aLong -> Flux.just("Hi!", "Hello!", "How are you?"));
    }

}
