package com.sonetto.be.metric.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.sonetto.be.metric.ai.PoetryAnalyst;
import com.sonetto.be.metric.model.SyllableToken;
import com.sonetto.be.metric.model.VerseAnalysis;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class MetricOrchestratorTest {

    @Inject
    MetricOrchestrator orchestrator;

    @InjectMock
    PoetryAnalyst mockAiService;

    @Test
    void testCacheEfficiency() {
        String verse = "Nel mezzo del cammin di nostra vita";

        VerseAnalysis mockResponse = new VerseAnalysis(
                verse,
                List.of(new SyllableToken("Nel", false, false, false, 1)),
                11,
                "Endecasillabo");

        Mockito.when(mockAiService.analyzeVerse(verse))
                .thenReturn(mockResponse);

        VerseAnalysis firstResponse = orchestrator.getAnalysis(verse).await().indefinitely();
        VerseAnalysis secondResponse = orchestrator.getAnalysis(verse).await().indefinitely();

        org.junit.jupiter.api.Assertions.assertEquals(11, firstResponse.totalMetricCount());
        org.junit.jupiter.api.Assertions.assertEquals(11, secondResponse.totalMetricCount());
        Mockito.verify(mockAiService, Mockito.times(1)).analyzeVerse(verse);
    }
    
}
