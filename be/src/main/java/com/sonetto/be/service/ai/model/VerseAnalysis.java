package com.sonetto.be.service.ai.model;

import java.util.List;

import dev.langchain4j.model.output.structured.Description;

public record VerseAnalysis(
        @Description("Verso originale fornito in input") String originalVerse,
        @Description("Sequenza delle sillabe metriche annotate") List<SyllableToken> tokens,
        @Description("Conteggio metrico totale del verso") int totalMetricCount,
        @Description("Tipologia metrica riconosciuta, ad esempio endecasillabo") String meterType
) {
}
