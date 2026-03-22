package com.sonetto.be.metric.service;

import com.sonetto.be.service.ai.PoetryAnalyst;
import com.sonetto.be.service.ai.model.VerseAnalysis;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.jboss.logging.Logger; // Logger standard di Quarkus
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

// IMPORT COMPLETO PER ASSERTNOTNULL (JUnit 5)
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@Tag("integration")
class PoetryAnalystLiveTest {

    private static final Logger LOG = Logger.getLogger(PoetryAnalystLiveTest.class);

    @Inject
    PoetryAnalyst realAnalyst;

    @Test
    void testRealGroqCall() {
        String verse = "Sempre caro mi fu quest'ermo colle";
        
        VerseAnalysis result = realAnalyst.analyzeVerse(verse);
        
        // Assert professionale
        assertNotNull(result, "Il risultato dell'analisi AI non dovrebbe essere null");
        
        // Logging a schermo (apparirà nel terminale durante il test)
        LOG.infof("--- RISULTATO ANALISI GROQ ---");
        LOG.infof("Verso: %s", result.originalVerse());
        LOG.infof("Metro: %s", result.meterType());
        LOG.infof("Sillabe Metriche: %d", result.totalMetricCount());
        LOG.infof("Tokens: %s", result.tokens());
        LOG.infof("-------------------------------");
    }
}