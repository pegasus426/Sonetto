package com.sonetto.be.service.ai;

import com.sonetto.be.service.ai.model.VerseAnalysis;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;

@RegisterAiService
@ApplicationScoped // Fondamentale per l'iniezione corretta in Quarkus
public interface PoetryAnalystAiService {

    @SystemMessage(fromResource = "prompts/it-IT-poetry-expert.txt")
    @UserMessage("""
            Esegui l'analisi filologica del seguente verso:
            ---
            {{verse}}
            ---
            Assicurati che totalMetricCount segua la regola dell'accento finale (posizione accento + 1).
            """)
    VerseAnalysis analyzeVerse(@V("verse") String verse);
}