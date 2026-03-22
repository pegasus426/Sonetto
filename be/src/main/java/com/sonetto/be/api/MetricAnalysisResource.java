package com.sonetto.be.api;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Schema; // <--- Import fondamentale

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

    private final MetricService metricService;

    public MetricAnalysisResource(final MetricService metricService) {
        this.metricService = metricService;
    }
    
    @Operation(summary = "Analizza la metrica di un verso", 
               description = "Analizza la metrica di un verso italiano")
    @POST
    @Path("/analyze")
    public Uni<VerseAnalysis> analyzeVerse(@Valid final AnalyzeVerseRequest request) {
        return metricService.getAnalysis(request.verse());
    }

    // Aggiungiamo lo Schema con l'esempio qui
    public record AnalyzeVerseRequest(
        @NotBlank 
        @Schema(description = "Il verso da analizzare", examples = "Nel mezzo del cammin di nostra vita") 
        String verse
    ) {
    }
}