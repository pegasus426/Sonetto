package com.sonetto.be.service;

import com.sonetto.be.service.ai.PoetryAnalyst;
import com.sonetto.be.service.ai.model.VerseAnalysis;

import io.quarkus.cache.CacheResult;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MetricOrchestrator {

    private final PoetryAnalyst poetryAnalyst;

    public MetricOrchestrator(final PoetryAnalyst poetryAnalyst) {
        this.poetryAnalyst = poetryAnalyst;
    }

    @CacheResult(cacheName = "poetry-metrics-cache")
    public Uni<VerseAnalysis> getAnalysis(final String verse) {
        return Uni.createFrom().item(() -> poetryAnalyst.analyzeVerse(verse));
    }

    
}
