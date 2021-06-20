package com.easy2excel.springCloudStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class SpringCloudStreamDemoService {

    @Bean
    Supplier<Flux<Long>> send() {
        return () -> Flux.interval(Duration.ofSeconds(1))
                .log();
    }

    @Bean
    Function<Flux<Long>, Flux<Long>> accumulate() {
        return longFlux -> longFlux
                .map(i -> i * i)
                .log();
    }

    @Bean
    Consumer<Long> receive() {
        return payload -> {
            System.out.println("payload data {}"+ payload);
        };
    }
}
