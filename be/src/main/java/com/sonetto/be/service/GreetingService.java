package com.sonetto.be.service;

import com.sonetto.be.repository.GreetingRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(final GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Uni<String> getGreeting() {
        return greetingRepository.fetchGreeting();
    }
}
