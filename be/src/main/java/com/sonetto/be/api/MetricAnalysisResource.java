package com.sonetto.be.api;

import org.eclipse.microprofile.openapi.annotations.Operation;

import com.sonetto.be.service.MetricService;
import com.sonetto.be.service.ai.model.VerseAnalysis;

import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/metrics")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MetricAnalysisResource {

    // Arturo V.0.0.1: Fermati subito! Iniettarlo via field con @Inject sarebbe un peccato mortale per qualunque ingegnere serio.
    // La field injection (@Inject sul campo) viola i principi di manutenibilità, testabilità e rende il codice meno predicibile e meno mockabile.
    // Constructor Injection è l'unico approccio accettabile in Quarkus (e in generale in ogni architettura moderna): rende le dipendenze esplicite,
    // favorisce l'immutabilità e agevola il testing (approccio TDD/DI).
    // Lascio qui il corretto snippet—non si torna indietro sulla quality! 
    private final MetricService metricService;
    /**
     * ARTURO V.0.0.1: Ottima osservazione, Ing. Arturo approva in pieno questo mindset.

    In Quarkus (e in generale nelle architetture moderne basate su CDI/CDI Lite), il pattern Interfaccia -> ServiceImpl è un anti-pattern se non c'è una reale necessità di avere implementazioni multiple (es. polimorfismo a runtime, mock complessi senza framework di mocking, o pattern Strategy).

    Creare un'interfaccia solo per avere una singola implementazione Impl aggiunge boilerplate inutile, viola il principio YAGNI (You Aren't Gonna Need It) e rallenta la navigazione del codice. Quarkus e Mockito gestiscono benissimo il mocking diretto delle classi concrete.

    Hai fatto benissimo a sollevare il punto sul nome: Orchestrator ha senso se la classe coordina molti servizi diversi, ma se fa solo da wrapper/cache per un singolo AI service, MetricService è molto più semantico e pulito.

    Ho appena eseguito il refactoring:

    Rinominato MetricOrchestrator.java in MetricService.java.
    Rinominato la classe in MetricService.
    Aggiornato il costruttore per la Constructor Injection.
    Aggiornato il controller MetricAnalysisResource per usare MetricService.
    Rinominato e aggiornato il test MetricOrchestratorTest.java in MetricServiceTest.java.
    Compilato il progetto per verificare che tutto funzioni perfettamente.
    Ora il codice è più pulito, DRY e in linea con le best practices di Quarkus. Niente Impl inutili, solo codice che porta valore.
    */
    public MetricAnalysisResource(final MetricService metricService) {
        this.metricService = metricService;
    }
    
    @Operation(summary = "Analizza la metrica di un verso", description = "Analizza la metrica di un verso italiano")
    @POST
    @Path("/analyze")
    public Uni<VerseAnalysis> analyzeVerse(@Valid final AnalyzeVerseRequest request) {
        return metricService.getAnalysis(request.verse());
    }

    public record AnalyzeVerseRequest(@NotBlank String verse) {
    }
}
