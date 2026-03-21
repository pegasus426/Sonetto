package com.sonetto.be.metric.service;

import com.sonetto.be.metric.ai.PoetryAnalyst;
import com.sonetto.be.metric.model.VerseAnalysis;

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
