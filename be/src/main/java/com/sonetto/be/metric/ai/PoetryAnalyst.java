package com.sonetto.be.metric.ai;

import com.sonetto.be.metric.model.VerseAnalysis;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface PoetryAnalyst {

    @SystemMessage("""
            Sei un esperto di metrica italiana.
            Analizza il verso in ingresso con rigore filologico.
            Identifica le sillabe metriche, segnala sinalefe e dieresi, individua gli accenti
            e classifica il metro restituendo esclusivamente una risposta strutturata.
            Ti sto invocando da un client programmatico, quindi non devi usare markdown o altri formati non strutturati
            e devi rispondere esclusivamente con un JSON valido.
            """)
    @UserMessage("Analizza metricamente il seguente verso italiano: {{verse}}")
    VerseAnalysis analyzeVerse(@V("verse") String verse);
}
