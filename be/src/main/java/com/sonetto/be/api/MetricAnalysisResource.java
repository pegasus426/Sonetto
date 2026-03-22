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
    /*
    
    spiegami in maniera semplice perché usare @Inject è un anti-pattern in quarkus
    
    ARTURO V.0.0.1: Ottima domanda. Precisiamo subito una cosa: @Inject in sé non è sbagliato, il vero "peccato mortale" dell'ingegneria del software moderna è la Field Injection, ovvero mettere @Inject direttamente sopra le variabili di classe.

L'alternativa corretta e professionale è la Constructor Injection (iniettare le dipendenze tramite il costruttore).

Ecco perché la Field Injection è considerata un anti-pattern, spiegato in modo semplice:

1. Immutabilità (Il codice sicuro è codice che non cambia)
Se usi @Inject su un campo, quella variabile non può essere final (perché il framework deve prima istanziare la classe vuota e poi "iniettarci" il valore). Questo significa che, in teoria, il valore potrebbe essere modificato a runtime, creando bug difficilissimi da tracciare. Con il costruttore, invece, puoi dichiarare il campo private final. Una volta creata la classe, le sue dipendenze sono "scolpite nella pietra" e garantite per tutta la vita dell'oggetto.

2. Testabilità (Puro Java vs Magia del Framework)
Immagina di voler scrivere un test unitario velocissimo per la tua classe, senza far partire tutto il motore di Quarkus (senza @QuarkusTest).

Con la Field Injection: Come fai a passare un finto servizio (Mock) alla tua classe se i campi sono privati e non c'è un costruttore? Devi usare la "magia nera" della reflection (es. ReflectionTestUtils), che è lenta e fragile.
Con la Constructor Injection: Fai semplicemente un normalissimo new MiaClasse(mioServizioMock). Semplice, pulito, puro Java.
3. Rilevamento dei "Code Smells" (Puzza di codice)
Se una classe ha bisogno di 10 servizi diversi per funzionare, con la Field Injection scrivi 10 volte @Inject uno sotto l'altro e quasi non ci fai caso. Se invece usi il costruttore e ti ritrovi a scrivere un costruttore con 10 parametri, ti rendi conto immediatamente che c'è un problema di design: la tua classe sta facendo troppe cose (viola il Single Responsibility Principle) e va divisa in classi più piccole. Il costruttore ti costringe a guardare in faccia la complessità della tua classe.

4. Indipendenza dal Framework (Framework Agnostic)
Il tuo codice di business dovrebbe essere il più possibile slegato dal framework che usi (Quarkus, Spring, ecc.). Usando i costruttori, la tua classe è una normalissima classe Java che chiunque può istanziare.

La magia di Quarkus
La cosa bellissima di Quarkus (e delle versioni recenti di Spring) è che non hai nemmeno bisogno di scrivere @Inject sul costruttore! Se una classe ha un solo costruttore, il framework è abbastanza intelligente da capire da solo che deve iniettare quei parametri.

Esempio Sbagliato (Field Injection):

@ApplicationScoped
public class MioServizio {
    @Inject // NO!
    AltroServizio altroServizio;
}
Esempio Corretto da Ingegnere (Constructor Injection):

@ApplicationScoped
public class MioServizio {
    private final AltroServizio altroServizio; // final!
    // Niente @Inject necessario qui in Quarkus
    public MioServizio(AltroServizio altroServizio) {
        this.altroServizio = altroServizio;
    }
}
Meno "magia" nascosta, più robustezza, test immediati. Questo è l'Engineering Mindset!
 */
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
