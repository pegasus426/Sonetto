package com.sonetto.be.metric.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.sonetto.be.service.ai.PoetryAnalystAiService;
import com.sonetto.be.service.ai.model.SyllableToken;
import com.sonetto.be.service.ai.model.VerseAnalysis;
import com.sonetto.be.service.MetricService;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class MetricServiceTest {

    @Inject
    MetricService metricService;

    @InjectMock
    PoetryAnalystAiService mockAiService;

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

        VerseAnalysis firstResponse = metricService.getAnalysis(verse).await().indefinitely();
        VerseAnalysis secondResponse = metricService.getAnalysis(verse).await().indefinitely();

        org.junit.jupiter.api.Assertions.assertEquals(11, firstResponse.totalMetricCount());
        org.junit.jupiter.api.Assertions.assertEquals(11, secondResponse.totalMetricCount());
        Mockito.verify(mockAiService, Mockito.times(1)).analyzeVerse(verse);
    }
    
}
