package com.sonetto.be.service;

import com.sonetto.be.service.ai.PoetryAnalystAiService;
import com.sonetto.be.service.ai.model.VerseAnalysis;

import io.quarkus.cache.CacheResult;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MetricService {

    private final PoetryAnalystAiService poetryAnalyst;

    public MetricService(final PoetryAnalystAiService poetryAnalyst) {
        this.poetryAnalyst = poetryAnalyst;
    }

    @CacheResult(cacheName = "poetry-metrics-cache")
    public Uni<VerseAnalysis> getAnalysis(final String verse) {
        // ARTURO V.0.0.1: La chiamata a poetryAnalyst.analyzeVerse(verse) è un'operazione bloccante (I/O verso l'API LLM).
        // Se la eseguiamo direttamente dentro l'item() senza specificare un thread pool diverso, 
        // Quarkus proverà ad eseguirla sull'Event Loop thread (vert.x-eventloop-thread-*).
        // Questo causa un BlockingNotAllowedException perché l'Event Loop non deve mai essere bloccato.
        // La soluzione è usare runSubscriptionOn() per delegare l'esecuzione a un worker thread.
        return Uni.createFrom().item(() -> poetryAnalyst.analyzeVerse(verse))
                .runSubscriptionOn(io.smallrye.mutiny.infrastructure.Infrastructure.getDefaultWorkerPool());
    }

    
}
