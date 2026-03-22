2. Trunk-Based Development (TBD): La via dei Senior
Dimentica GitFlow. GitFlow è per chi ha paura di rompere il codice e finisce per creare "Integration Hell" con branch che vivono per settimane. Un vero Senior Architect punta alla Trunk-Based Development.

Cos'è?
Tutti gli sviluppatori lavorano su un'unica branca principale chiamata trunk (o main).

Le Regole d'Oro di Arturo per la TBD:
Short-Lived Branches: Se crei un branch, deve durare al massimo poche ore o un giorno. Se dura di più, il tuo task è troppo grande. Spezzalo.

Continuous Integration (CI): Ogni commit su main deve far scattare build e test automatizzati. Se la build fallisce, la priorità assoluta di tutto il team è ripararla. Subito.

Feature Flags (Fondamentali): Come fai a pushare codice non finito in produzione? Usi i Feature Flags. Il codice è nel main, viene deployato, ma è spento da un parametro di configurazione. Questo disaccoppia il Deployment (atto tecnico) dalla Release (atto di business).

Pair Programming o Synchronous Reviews: Poiché il ritmo è serrato, le code review devono essere veloci. Non lasciare una PR appesa per due giorni; distrugge la velocità del team.

Perché usarla con Quarkus?
Quarkus è nato per il Cloud-Native e la velocità. La TBD si sposa perfettamente con i cicli di vita di Quarkus (Fast Jar, Native Compilation). Ti permette di avere un feedback loop immediato: scrivi, testi (con i Dev Services), pushi su main, e il tuo CI/CD porta il microservizio in staging in pochi minuti.





> spiegami se nella trunk based development è accettabile fare piccoli commit di refactoring o fix direttamente sul main

Arturo
Gem personalizzato
Arturo ha detto
Nella Trunk-Based Development (TBD), la risposta breve è: Sì, non solo è accettabile, è caldamente raccomandato.

Il principio cardine della TBD è che il main (o trunk) sia l'unica fonte di verità e che sia sempre in uno stato rilasciabile. Fare piccoli commit direttamente sul main è l'essenza stessa di questo approccio, a patto di seguire alcune regole ferree.

Ecco come Arturo (il tuo Senior Dev di fiducia) approccerebbe la questione:

1. Il mantra: "Small, Frequent Commits"
In TBD, l'obiettivo è evitare i "Merge Hell" (conflitti giganti). Se hai un fix da 3 righe o un refactoring che non rompe la compatibilità (es. rinominare una variabile o estrarre un metodo), spingerlo direttamente sul main:

Riduce il "Inventory Waste": Il codice non ancora mergiato è "merce ferma" che non produce valore.

Migliora la visibilità: Tutti i colleghi ricevono subito il tuo refactoring, evitando che scrivano nuovo codice basato su vecchie astrazioni.

2. Quando è accettabile (e quando no)
Non è un "liberi tutti". Esistono dei guardrail:

Tipo di Modifica	Accettabile su Main?	Requisito
Fix urgente	✅ Sì	Deve avere un test unitario a corredo.
Refactoring Atomico	✅ Sì	Deve superare tutti i test locali (e la pipeline CI).
Nuova Feature	⚠️ Dipende	Solo se è "nascosta" dietro una Feature Flag.
Breaking Change	❌ No	Richiede una strategia di migrazione o un brevissimo branch di coordinamento.
3. La regola del "10-minute Build"
Nella TBD, se committi sul main, devi essere certo che la CI (Continuous Integration) sia velocissima. Se il tuo piccolo commit rompe il main, "blocchi la catena di montaggio".

Arturo's Rule: "Se rompi il main, la tua unica priorità è fixarlo o fare revert immediato. Non si va a prendere il caffè finché il main non è di nuovo verde."

4. Pair Programming e "Synchronous Review"
Se lavori direttamente sul main, salti il processo asincrono delle Pull Request (che spesso rallenta il flusso). Per mantenere la qualità:

Si usa spesso il Pair Programming: Se due persone guardano lo schermo, la "code review" avviene in tempo reale mentre scrivi.

Si usano i Pre-commit hooks: Script che impediscono il push se i test o il linter falliscono.

5. Perché è meglio dei branch a lungo termine?
Immagina di fare un refactoring di una classe core in un branch separato per 3 giorni. Nel frattempo, altri 4 dev scrivono codice su quella classe nel main. Al momento del merge, avrai un conflitto logico micidiale.
Facendo piccoli commit sul main, "distribuisci" il cambiamento un pezzetto alla volta.

Conclusione: La mentalità del Chirurgo
Fare commit sul main richiede disciplina. Non è per chi "ci prova e vede se va". È per chi:

Scrive test prima del codice.

Fa build locali prima del push.

Divide i task in pezzi così piccoli da essere "innocui".

Ora che abbiamo chiarito la filosofia di sviluppo, vuoi che torniamo al nostro progetto Sonetto e configuriamo il primo servizio Angular per ricevere i dati dal backend, magari proprio seguendo questa logica di piccoli commit incrementali?





> come è la convenzione di naming per i branch di breve durata con trunk based development?

Arturo
Gem personalizzato
Arturo ha detto
Se vuoi abbracciare la Trunk-based Development (TBD), devi cambiare radicalmente prospettiva rispetto al classico Git Flow. In TBD, il nome del branch non serve a descrivere una "storia d'amore" lunga due settimane con una funzionalità; serve solo come puntatore temporaneo verso il main.

Le convenzioni devono essere snelle, piatte e orientate all'azione. Ecco come un ingegnere serio nomina i propri branch a vita breve (Short-lived branches):

1. La Struttura Standard (Short-lived)
Dimentica gerarchie complesse come feature/release/v1/sub-task/.... Usa un prefisso funzionale seguito dall'ID del ticket (Jira/GitHub) e una descrizione telegrafica.

Schema: tipo/ID-ticket-descrizione-breve

Prefisso	Scopo	Esempio
feat/	Nuova funzionalità (piccola!)	feat/DEV-123-add-user-cache
fix/	Bug fixing immediato	fix/DEV-456-null-pointer-kafka
ref/	Refactoring del codice (senza cambi logici)	ref/cleanup-panache-repository
test/	Aggiunta di test unitari o integration	test/add-mutiny-pipeline-tests
chore/	Aggiornamento dipendenze o config CI/CD	chore/update-quarkus-3-15
2. Le Regole d'Oro di Arturo per il Naming
Se vedo un branch chiamato feature/rifacimento-totale-backend, lo cancello prima ancora che tu faccia il push. Ecco perché:

Niente nomi personali: Non chiamare il branch arturo/my-code. Il codice è del team, non tuo.

Kebab-case: Usa sempre il trattino (-), mai underscore o spazi. È lo standard per la leggibilità nei terminali e nelle URL dei sistemi di CI/CD.

Sii atomico: Se il nome del branch contiene la congiunzione "and" (es. feat/add-login-and-fix-footer), stai violando il principio di Singola Responsabilità. Dividi in due branch diversi.

Il prefisso è un impegno: Se inizi con fix/, non voglio vedere nuove feature nel diff della Pull Request. Sii disciplinato.

3. Il "Scalping" (Branch senza Ticket)
Nella TBD pura, per modifiche che richiedono meno di un'ora (es. correggere un typo o un log), puoi anche usare nomi ultra-rapidi se il team lo permette:

patch/fix-typo-readme

quick/update-api-timeout

4. Il Workflow d'Ingegneria
In TBD, il nome del branch è quasi irrilevante perché il branch deve morire entro 24 ore.

Pro-Tip di Arturo: "Se il tuo branch ha bisogno di un nome complesso per essere spiegato, significa che è troppo grande. In TBD, un branch è come un post-it: lo scrivi, lo usi, lo butti. Il vero 'Journal' della tua intelligenza deve essere il Commit Message, non il nome del branch."

Esempio Pratico su Quarkus:
Supponiamo tu debba aggiungere un filtro reattivo per il logging dei tempi di risposta:

Branch: feat/QUARK-101-response-time-filter

Commit 1: feat: add Mutiny filter for request latency

Commit 2: test: verify latency header in integration tests

Merge su main e cancellazione branch.

Vuoi che ti spieghi come configurare un hook di Git (Husky) per impedirti di creare branch che non rispettano queste convenzioni? Così automatizziamo la disciplina.



> spiegami come si usa non rompere la produzione un un piccolo esempio quarkus e come si può usare i feature flags insieme al testing ci

Arturo
Gem personalizzato
Arturo ha detto
Ascolta bene, perché qui passiamo dalla teoria alla "battaglia". In Trunk-Based Development, il segreto per non rompere la produzione non è testare di più (anche se è necessario), ma disaccoppiare il Deployment dalla Release.

Il codice viene deployato continuamente, ma la funzionalità rimane "dormiente" finché non decidiamo che è sicura.

1. Implementare i Feature Flags in Quarkus
In Quarkus, il modo più "ingegneristico" per gestire i feature flags senza sporcare il codice con troppi if/else è usare le Configurazioni Dinamiche o librerie dedicate come Unleash o Togglz.

Ma iniziamo dalle basi: usare @ConfigProperty per un controllo granulare.

Esempio: Nuovo servizio di Sconto Reattivo
Immagina di voler cambiare la logica di calcolo sconti, ma non sei sicuro che il nuovo algoritmo regga il carico.

Java
@ApplicationScoped
public class DiscountService {

    @ConfigProperty(name = "features.new-discount-logic.enabled", defaultValue = "false")
    boolean newLogicEnabled;

    @Inject
    LegacyDiscountCalculator legacyCalculator;

    @Inject
    NewReactiveDiscountCalculator newCalculator;

    public Uni<Double> calculate(User user) {
        // La logica di routing: il codice "nuovo" è già in produzione, ma silente.
        if (newLogicEnabled) {
            return newCalculator.calculate(user);
        }
        return Uni.createFrom().item(legacyCalculator.calculate(user));
    }
}
Perché questo è "Engineering Mindset"?
Kill Switch: Se il newCalculator lancia eccezioni in produzione, ti basta cambiare una variabile d'ambiente (es. in Kubernetes o via ConfigMap) a false senza dover rifare il deploy del JAR.

Canary Release: Puoi attivare la flag solo per una percentuale di nodi o per utenti "Beta".

2. Feature Flags e Testing CI/CD
Se il codice è "spento", come facciamo a testarlo nella Pipeline? Qui molti cadono, ma un vero Architect usa la Matrix Strategy.

A. Unit e Integration Testing (JUnit 5)
Non testare solo il default. Devi testare entrambi i rami del tuo if. In Quarkus puoi usare @TestProfile per simulare diverse configurazioni.

Java
@QuarkusTest
@TestProfile(NewLogicEnabledProfile.class) // Forza la flag a true per questo test
class DiscountServiceTest {
    @Inject
    DiscountService service;

    @Test
    void testNewLogic() {
        // Verifica che la nuova logica funzioni correttamente
    }
}
B. La Pipeline CI (GitHub Actions / GitLab CI)
La tua pipeline deve essere spietata. Ecco il flusso:

Build: Compilazione e check dei test con flag di default.

Parallel Test: Esegui una matrice di test dove forzi le feature flag critiche a true. Se i test del "nuovo codice" falliscono, la build del main (il trunk) fallisce.

Artifact Creation: Se tutto passa, produci l'immagine Docker.

3. Come non rompere la produzione (Il decalogo di Arturo)
"Il rischio zero non esiste, esiste solo una gestione del rischio superiore."

Backward Compatibility: Se il nuovo codice richiede una nuova colonna sul database, aggiungila prima con una migrazione (Flyway/Liquibase). Non cancellare mai la vecchia colonna finché il vecchio codice non è stato rimosso.

Observability: Quando attivi una flag, guarda i log. Se vedi un picco di 500 Error, spegni la flag immediatamente.

Dark Launching: Invia i dati a entrambi i servizi (vecchio e nuovo), ma usa solo il risultato del vecchio. Compara i risultati nei log per vedere se il nuovo servizio si comporta come previsto.

Clean Up: Una volta che la feature è stabile al 100%, elimina il Feature Flag. Il debito tecnico si annida negli if/else dimenticati. Un branch di "Chore" per pulire i vecchi flag è un segno di maturità estrema.

Il prossimo passo verso l'eccellenza
Abbiamo parlato di come separare il codice. Ti va di vedere come configurare una Pipeline GitHub Actions che esegue automaticamente i test su Quarkus usando i Dev Services (Docker) per simulare un database reale durante la CI? È il modo definitivo per garantire che il tuo "Trunk" sia sempre pronto per il rilascio.


> trunk based development




Trunk-Based Development (TBD) is a version control management strategy where developers merge code changes into a single central branch (the "trunk" or main) multiple times a day. By using short-lived feature branches or direct commits, teams minimize merge conflicts, enable true continuous integration, and improve collaboration. 
Optimizely
Optimizely
 +4
Key Aspects of Trunk-Based Development:
Frequent Merges: Developers push code to the main branch at least once a day, avoiding long-lived branches that diverge from the main codebase.
Continuous Integration (CI): It acts as a prerequisite for CI, as frequent integration allows for immediate automated testing and validation.
Reduced Merge Conflicts: Small, incremental updates make code integration simpler and less risky than traditional, long-lived branch models like Gitflow.
Scalability: Small teams often commit directly to the trunk, while larger teams use short-lived branches (lasting a few hours or days) to manage complexity, according to Harness.
Code Review Efficiency: Because changes are smaller, reviews are faster and more focused, often supported by automated testing, say Atlassian. 
Atlassian
Atlassian
 +5
Advantages
Faster Feedback: Developers immediately know if their changes break existing functionality.
Collaboration: Encourages better team communication and shared ownership of code.
Elite Performance: Identified by semaphore.io as a core practice for high-performing, elite software teams to deliver faster, according to dora.dev. 
semaphore.io
semaphore.io
 +3
Implementation Tips
Invest in robust automated testing to ensure the trunk is always in a deployable state, says Unleash.
Use feature flags to manage incomplete features in production.
Keep branches short-lived; delete them after merging. 
Medium
Medium
 +4
