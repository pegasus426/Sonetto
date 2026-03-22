package com.sonetto.be.service.ai;

import com.sonetto.be.service.ai.model.VerseAnalysis;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface PoetryAnalystAiService {

    @SystemMessage("""
            Sei un esperto di metrica italiana.
            Analizza il verso in ingresso con rigore filologico.
            Identifica le sillabe metriche, segnala sinalefe e dieresi, individua gli accenti
            e classifica il metro restituendo esclusivamente una risposta strutturata.
            Ti sto invocando da un client programmatico, quindi non devi usare markdown o altri formati non strutturati
            e devi rispondere esclusivamente con un JSON valido.
        
             Dividi ogni parola in sillabe grammaticali prima di applicare le figure metriche. 
             Definisci la Sinalefe: "La sinalefe avviene solo tra la vocale finale di una parola e 
             la vocale iniziale della parola successiva.
             Forza il conteggio: Ricorda che un endecasillabo piano ha l'ultimo accento sulla decima sillaba e conta come 11.    
        """)
    @UserMessage("Analizza metricamente il seguente verso italiano: {{verse}}")
    VerseAnalysis analyzeVerse(@V("verse") String verse);
}
