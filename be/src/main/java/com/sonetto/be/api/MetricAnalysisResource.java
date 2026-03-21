package com.sonetto.be.api;

import org.eclipse.microprofile.openapi.annotations.Operation;

import com.sonetto.be.metric.model.VerseAnalysis;
import com.sonetto.be.metric.service.MetricOrchestrator;

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

    private final MetricOrchestrator metricOrchestrator;

    public MetricAnalysisResource(final MetricOrchestrator metricOrchestrator) {
        this.metricOrchestrator = metricOrchestrator;
    }
    
    @Operation(summary = "Analizza la metrica di un verso", description = "Analizza la metrica di un verso italiano")
    @POST
    @Path("/analyze")
    public Uni<VerseAnalysis> analyzeVerse(@Valid final AnalyzeVerseRequest request) {
        return metricOrchestrator.getAnalysis(request.verse());
    }

    public record AnalyzeVerseRequest(@NotBlank String verse) {
    }
}
