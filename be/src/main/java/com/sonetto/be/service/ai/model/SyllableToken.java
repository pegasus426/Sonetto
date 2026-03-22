package com.sonetto.be.service.ai.model;

import dev.langchain4j.model.output.structured.Description;

public record SyllableToken(
        @Description("Testo della sillaba") String text,
        @Description("Vero se avviene sinalefe con la successiva") boolean isSinalefe,
        @Description("Vero se e presente una dieresi") boolean isDieresi,
        @Description("Vero se la sillaba e accentata") boolean isAccented,
        @Description("Posizione progressiva della sillaba nel verso") int position
) {
}
