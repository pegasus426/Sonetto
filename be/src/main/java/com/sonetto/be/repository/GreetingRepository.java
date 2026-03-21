package com.sonetto.be.repository;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingRepository {

    public Uni<String> fetchGreeting() {
        return Uni.createFrom().item("pong");
    }
}
